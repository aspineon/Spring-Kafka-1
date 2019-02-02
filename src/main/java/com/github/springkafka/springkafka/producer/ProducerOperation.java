package com.github.springkafka.springkafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springkafka.springkafka.configuration.KafkaConfiguration;
import com.github.springkafka.springkafka.model.TrainReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Import({KafkaProducerConfig.class})
@Component
public class ProducerOperation {

    Logger logger = LoggerFactory.getLogger(ProducerOperation.class);

    @Autowired
    KafkaTemplate<String, TrainReservation> kafkaTemplate;

    @Autowired
    KafkaConfiguration kafkaConfiguration;

    @Autowired
    ObjectMapper objectMapper;

    public void sendMessage() {

        for (int i = 1; i <= 50; i++) {
            TrainReservation trainReservation = new TrainReservation("Nikhil", "B" + i, "Ghazipur", "PATNA");
            logger.info("Producing message - {} ", trainReservation.toString());
            kafkaTemplate.send(new GenericMessage<>(trainReservation));
        }
    }

}
