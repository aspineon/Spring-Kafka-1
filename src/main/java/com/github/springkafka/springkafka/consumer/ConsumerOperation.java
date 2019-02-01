package com.github.springkafka.springkafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springkafka.springkafka.configuration.KafkaConfiguration;
import com.github.springkafka.springkafka.consumer.KafkaConsumerConfig;
import com.github.springkafka.springkafka.model.TrainReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;

@EnableKafka
@Component
public class ConsumerOperation {

    Logger logger = LoggerFactory.getLogger(ConsumerOperation.class);

    @Autowired
    KafkaConfiguration kafkaConfiguration;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topicName}", containerFactory = "kafkaListenerContainerFactory") // Important point to note
    public void listen(@Payload TrainReservation message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {
        logger.info("Consuming message - {} , patition id - {} ", message, partition);
        logger.info("Consuming message and after deserilization - {} ", message.toString());
    }
}
