package com.example.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class ProducerClasse {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("group.id", "test-group");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");

        Producer<String, String> producer = new KafkaProducer<String,String>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("test-topic", Integer.toString(i), "Message " + i));
        }
        producer.close();
    }
}
