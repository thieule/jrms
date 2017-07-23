package com.thieule.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thieule.rms.model.Booking;
import com.thieule.rms.model.Employee;
import com.thieule.rms.repo.BookingRepository;
import com.thieule.rms.repo.EmployeeRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.POST)
	public Booking create(@RequestBody Booking booking) {
		Booking result = bookingRepository.save(booking);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{bookingId}")
	public Booking get(@PathVariable String bookingId) {
		return bookingRepository.findOne(bookingId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public List<Booking> getAll() {
		return bookingRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/employee")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
}
