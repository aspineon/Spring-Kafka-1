package com.github.springkafka.springkafka;

import com.github.springkafka.springkafka.producer.ProducerOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringkafkaApplication implements ApplicationRunner {

    @Autowired
    ProducerOperation producerOperation;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringkafkaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        producerOperation.sendMessage();
    }
}

