package com.thieule.rms.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service("kafkaMessageProducer")
public class KafkaMessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

        @Value(value = "thieu")
        private String topicName;

        @Value(value = "partionedTopicName")
        private String partionedTopicName;

        @Value(value = "filteredTopicName")
        private String filteredTopicName;

        @Value(value = "greenting")
        private String greetingTopicName;

        public void sendMessage(String message) {
            kafkaTemplate.send(topicName, message);
        }

        public void sendMessageToPartion(String message, int partition) {
            kafkaTemplate.send(partionedTopicName, partition, message);
        }

        public void sendMessageToFiltered(String message) {
            kafkaTemplate.send(filteredTopicName, message);
        }

        public void sendGreetingMessage(Greeting greeting) {
            greetingKafkaTemplate.send(greetingTopicName, greeting);
        }
 }


