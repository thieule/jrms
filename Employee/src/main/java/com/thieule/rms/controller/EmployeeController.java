package com.thieule.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.thieule.rms.model.Employee;
import com.thieule.rms.repo.EmployeeRepository;
import com.thieule.rms.service.KafkaMessageProducer;


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
		/*
         * Send to Kafka
         */
		kafkaMessageProducer.sendEmployeeMessage(result);
		
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{employeeId}")
	public Employee get(@PathVariable String employeeId){
		return employeeRepository.findOne(employeeId);
	}
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, value="")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
}
