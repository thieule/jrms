package com.thieule.rms.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="project")
public class Project {

	
	@Id
	private String id; 
	private String name;
	private String description;
	private Integer estimate_time;
	private Integer estimate_resource;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getEstimate_time() {
		return estimate_time;
	}
	public void setEstimate_time(Integer estimate_time) {
		this.estimate_time = estimate_time;
	}
	public Integer getEstimate_resource() {
		return estimate_resource;
	}
	public void setEstimate_resource(Integer estimate_resource) {
		this.estimate_resource = estimate_resource;
	}
}
