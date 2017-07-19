package com.thieule.rms.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thieule.rms.employee.model.Employee;
import com.thieule.rms.employee.repo.EmployeeRepository;
import com.thieule.rms.employee.service.KafkaMessageProducer;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	KafkaMessageProducer kafkaMessageProducer;
	
    
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
	public List<Employee> getAll() {
        
        /*
         * Kafka process
         */
		kafkaMessageProducer.sendMessage("Trigger from all employee page");
        
		return employeeRepository.findAll();
	}
	
}
