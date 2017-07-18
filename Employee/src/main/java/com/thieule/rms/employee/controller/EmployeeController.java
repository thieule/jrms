package com.thieule.rms.employee.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thieule.rms.employee.model.Employee;
import com.thieule.rms.employee.repo.EmployeeRepository;
import com.thieule.rms.employee.service.Greeting;
import com.thieule.rms.employee.service.Kafka;
import com.thieule.rms.employee.service.Kafka.MessageListener;
import com.thieule.rms.employee.service.Kafka.MessageProducer;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
    
	@RequestMapping(method = RequestMethod.POST)
	public Employee create(@RequestBody Employee employee){
		Employee result = employeeRepository.save(employee);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{employeeId}")
	public Employee get(@PathVariable String employeeId){
		return employeeRepository.findOne(employeeId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="")
	public List<Employee> getAll(){
        
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Kafka.class);
		MessageProducer producer = (MessageProducer) ctx.getBean("messageProducer");
		
        /*
         * Sending a Hello World message to topic 'baeldung'. 
         * Must be recieved by both listeners with group foo
         * and bar with containerFactory fooKafkaListenerContainerFactory
         * and barKafkaListenerContainerFactory respectively.
         * It will also be recieved by the listener with
         * headersKafkaListenerContainerFactory as container factory
         */
        producer.sendMessage("Hello, World!");
        
        
		return employeeRepository.findAll();
	}
	
}
