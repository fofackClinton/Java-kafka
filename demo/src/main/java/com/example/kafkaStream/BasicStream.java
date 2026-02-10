package com.example.kafkaStream;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicStream {
    private static final Logger logger = LoggerFactory.getLogger(BasicStream.class);
    public static final String INPUT_TOPIC = "test-topic";
    public static final String OUTPUT_TOPIC = "output-topic";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        final StreamsBuilder builder = new StreamsBuilder();
        final KStream<String, String> source = builder.stream(INPUT_TOPIC);

        //filtrage des données
        final KStream<String, String> filtered = source.filter((key, value) -> value.length() > 5)
               .mapValues(value -> value.toUpperCase()); 

        filtered.to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));
        try (KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), props)) {
            kafkaStreams.start();
        } catch (StreamsException | IllegalStateException e) {
            logger.error("Kafka Streams error occurred", e);
        }    
    }
}
