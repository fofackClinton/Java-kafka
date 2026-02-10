package com.example.consumers;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {
    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");

        
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            consumer.subscribe(Arrays.asList("test-topic"));

            System.out.println("Consumer started, polling topic 'test-topic'");
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(java.time.Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset=%d, key=%s, value=%s%n", record.offset(), record.key(), record.value());
                }
            }
        } catch (org.apache.kafka.common.errors.WakeupException we) {
            // ignore - shutdown requested
        } catch (Exception e) {
            LOGGER.severe(() -> "An error occurred in the consumer: " + e.getMessage());
        }
    }

}
