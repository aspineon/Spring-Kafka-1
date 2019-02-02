package com.github.springkafka.springkafka.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("kafka")
public class KafkaConfiguration {
    private String topicName;
    private String bootstrapservers;
    private String groupId;
    private int topicPartition;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getBootstrapservers() {
        return bootstrapservers;
    }

    public void setBootstrapservers(String bootstrapservers) {
        this.bootstrapservers = bootstrapservers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setTopicPartition(int topicPartition) {
        this.topicPartition = topicPartition;
    }

    public Integer getPartition() {
        return topicPartition;
    }
}
