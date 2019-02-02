package WithoutSpringKafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class ConsumerWithThreads {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ConsumerWithThreads.class);
        String bootstrapservers = "127.0.0.1:9092";
        String groupId = "my-sixth-application";
        String topic = "first_topic";

        //Latch for dealing with multiple threads
        CountDownLatch countDownLatch = new CountDownLatch(1);
        logger.info("creating consumer thread ");

        // Creating consumer runnable
        Runnable myConsumerRunnable  = new ConsumerRunnable(bootstrapservers, groupId, topic, countDownLatch);

        //Start the thread
        Thread mythread = new Thread(myConsumerRunnable);
        mythread.start();

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            logger.info("Caught shutdown hook...");
            ((ConsumerRunnable) myConsumerRunnable).shutdown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Application has exited !!!");
        }

        ));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("application got interrupted ", e);
        }finally {
            logger.info("Application is closing");
        }



    }

}

class ConsumerRunnable implements Runnable{

    private CountDownLatch countDownLatch;
    private KafkaConsumer<String, String> kafkaConsumer;
    private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    public ConsumerRunnable(String bootStrapServers, String groupId, String topic, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        // create consumer configuration
        Properties properties  = new Properties();

        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(GROUP_ID_CONFIG, groupId);
        properties.setProperty(AUTO_OFFSET_RESET_CONFIG,"earliest"); // 3 options --> earliest,latest,none
        this.kafkaConsumer = new KafkaConsumer<String, String>(properties);
        this.kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
    //poll for new data
        try
        {
            while (true){
                ConsumerRecords<String, String> consumerRecord =
                        kafkaConsumer.poll(Duration.ofMillis(100));// new in kafka 2.0.0

                for(ConsumerRecord<String, String> record: consumerRecord){
                    logger.info("Key : " + record.key() + " Value : " + record.value());
                    logger.info("Partition : "+ record.partition() + " Offset : " + record.offset());
                }
            }
        } catch (WakeupException w){
                logger.info("Received shtudown signal.");
        }
        finally {
            kafkaConsumer.close();

            // tell our main code we are done with this consumer thread
            countDownLatch.countDown();
        }

    }

    public void shutdown(){
        // the wakeup is a special method to interrupt consumer.poll()
        // will throw exception WakeUpException
        kafkaConsumer.wakeup();
    }
}