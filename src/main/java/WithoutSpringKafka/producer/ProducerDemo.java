package WithoutSpringKafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class ProducerDemo {
    public static void main(String[] args) {
        // create producer configuration
        Properties properties  = new Properties();
        String boottrapservers = "127.0.0.1:9092";
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, boottrapservers);
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //create Producer record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first_topic", "hello Word");


        // send message - asynchronous
        kafkaProducer.send(producerRecord);

        kafkaProducer.flush();
        kafkaProducer.close();

    }
}
