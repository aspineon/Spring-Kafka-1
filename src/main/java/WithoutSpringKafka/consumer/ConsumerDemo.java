package WithoutSpringKafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class ConsumerDemo {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);

        // create consumer configuration
        Properties properties  = new Properties();
        String bootstrapservers = "127.0.0.1:9092";
        String groupId = "my-fourth-application";
        String topic = "first_topic";
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(GROUP_ID_CONFIG, groupId);
        properties.setProperty(AUTO_OFFSET_RESET_CONFIG,"earliest"); // 3 options --> earliest,latest,none

        // create consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        // subscribe our consumer to topic(s)
        kafkaConsumer.subscribe(Arrays.asList(topic));

        // poll message from topic
        while (true){
//            kafkaConsumer.poll(100);  deprecated
            ConsumerRecords<String, String> consumerRecord =
                    kafkaConsumer.poll(Duration.ofMillis(100));// new in kafka 2.0.0

            for(ConsumerRecord<String, String> record: consumerRecord){
                logger.info("Key : " + record.key() + " Value : " + record.value());
                logger.info("Partition : "+ record.partition() + " Offset : " + record.offset());
            }
        }

    }
}
