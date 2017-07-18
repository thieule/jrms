package com.thieule.rms.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class EmployeeBoot {

	   public static void main(String[] args) {
	        SpringApplication.run(EmployeeBoot.class);
	    }
	   
	   @Autowired
		private Environment env;

	   
}
