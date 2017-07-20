package com.thieule.rms.employee.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListerner {
	
	public CountDownLatch latch = new CountDownLatch(3);

    public CountDownLatch partitionLatch = new CountDownLatch(2);

    public CountDownLatch filterLatch = new CountDownLatch(2);

    public CountDownLatch greetingLatch = new CountDownLatch(1);

    @KafkaListener(topics = "thieu", group = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Messasge in group 'foo': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "thieu", group = "bar", containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message) {
        System.out.println("Received Messasge in group 'bar': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "thieu", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Messasge: " + message + " from partition: " + partition);
        latch.countDown();
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "thieu", partitions = { "0", "3" }))
    public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        this.partitionLatch.countDown();
    }

    @KafkaListener(topics = "filteredTopicName", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Recieved Message in filtered listener: " + message);
        this.filterLatch.countDown();
    }

    @KafkaListener(topics = "greenting", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Recieved greeting message: " + greeting);
        this.greetingLatch.countDown();
    }
}
