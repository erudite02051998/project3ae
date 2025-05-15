package com.aeCoder.project3ae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Project;
import com.aeCoder.project3ae.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private IdGeneratorService idGeneratorService;

	@Autowired
	private ProjectRepository projectRepository;

	public Project createProject(Project projectInput) {
		String newId = idGeneratorService.generateNextId(); // Sinh ID trước
		Project project = new Project(newId, projectInput.getTitle(), projectInput.getDescription(), projectInput.getCreatedAt(), projectInput.getUserId(), projectInput.getProjectPath());
		return projectRepository.save(project); // Lưu vào database
	}
	
	public Optional<Project> getProjectById(String projectId) {
		return projectRepository.findById(projectId);
	}
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
}
