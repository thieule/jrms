package com.thieule.rms.project.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thieule.rms.project.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
