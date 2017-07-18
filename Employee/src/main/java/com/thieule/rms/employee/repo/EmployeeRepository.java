package com.thieule.rms.employee.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thieule.rms.employee.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
