package WithoutSpringKafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class ProducerDemoWithKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Logger logger = LoggerFactory.getLogger(ProducerDemoWithKeys.class);

        // create producer configuration
        Properties properties  = new Properties();
        String boottrapservers = "127.0.0.1:9092";
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, boottrapservers);
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);


        for (int i=1;i<=10;i++)
        {
            //create Producer record
            String topicName = "first_topic";
            String value = "Message - " + i;
            final String key = "id_"+ i;

            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName, key, value);

            // send message - asynchronous
            kafkaProducer.send(producerRecord, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //executes every time a record is successfully send or an exception is thrown
                    if (e==null){
                        // record was successfully sent
                        logger.info("Received new metadata - \n"+
                                " Topic: "+ recordMetadata.topic() + "\n"+
                                " Key : "+ key + "\n"+
                                " Partition: " + recordMetadata.partition() + "\n" +
                                " Offset : " + recordMetadata.offset() + "\n" +
                                " TimeStamp: " +recordMetadata.timestamp() );

                    }
                    else
                    {
                        logger.error("Exception has occured - "+ e);
                    }
                }
            }).get();  //.get() block send() method, thereby making it synchronous- don't do it in production
        }

        kafkaProducer.flush();
        kafkaProducer.close();

    }
}
