package com.thieule.rms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.thieule.rms.model.Employee;

public class KafkaMessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, Employee> employeeKafkaTemplate;

        @Value(value = "basicTopic")
        private String topicName;

        @Value(value = "employeeTopic")
        private String emloyeeTopic;

        public void sendMessage(String message) {
            kafkaTemplate.send(topicName, message);
        }

        public void sendEmployeeMessage(Employee employee) {
        	employeeKafkaTemplate.send(emloyeeTopic, employee);
        }
 }


