package com.thieule.rms.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import com.thieule.rms.model.Employee;
import com.thieule.rms.repo.EmployeeRepository;

public class KafkaMessageListerner {
	
    public CountDownLatch employeeLatch = new CountDownLatch(1);
    
    @Autowired
	EmployeeRepository employeeRepository;
    
    @KafkaListener(topics = "employeeTopic", containerFactory = "employeeKafkaListenerContainerFactory")
    public void employeeListener(Employee employee) {
        System.out.println("Recieved employee message: " + employee.toString());
        employeeRepository.save(employee);
        this.employeeLatch.countDown();
    }
}
