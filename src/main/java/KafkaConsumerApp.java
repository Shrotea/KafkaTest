import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerApp {

    public static void main(String args[]) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaConsumer myConsumer = new KafkaConsumer(props);

        ArrayList<String> topics = new ArrayList<String>();
        topics.add("myTopic");
        topics.add("testTopic");

        myConsumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = myConsumer.poll(100);
                for (ConsumerRecord record : records) {
                    System.out.println("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myConsumer.close();
        }
    }
}
