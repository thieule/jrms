package com.thieule.rms.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thieule.rms.project.model.Project;
import com.thieule.rms.project.repo.ProjectRepository;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping(method = RequestMethod.POST)
	public Project create(@RequestBody Project project){
		
		Project result = projectRepository.save(project);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{projectId}")
	public Project get(@PathVariable String projectId){
		return projectRepository.findOne(projectId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/")
	public List<Project> getAll(){
		return projectRepository.findAll();
	}
	
}
