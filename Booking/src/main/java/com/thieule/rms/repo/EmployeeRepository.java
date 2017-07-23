package com.thieule.rms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.thieule.rms.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
