package com.aeCoder.project3ae.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.dto.ProjectDTO;
import com.aeCoder.project3ae.entity.Project;
import com.aeCoder.project3ae.repository.CategoryRepository;
import com.aeCoder.project3ae.repository.ProjectRepository;
import com.aeCoder.project3ae.repository.TechnicalRepository;

@Service
public class ProjectService {

	@Autowired
	private IdGeneratorService idGeneratorService;

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TechnicalRepository technicalRepository;

	public Project createProject(Project projectInput) {
		String newId = idGeneratorService.generateNextId(); // Sinh ID trước
		Project project = new Project(newId, projectInput.getTitle(), projectInput.getDescription(),
				projectInput.getCreatedAt(), projectInput.getUserId(), projectInput.getProjectPath());
		return projectRepository.save(project); // Lưu vào database
	}

	public Optional<Project> getProjectById(String projectId) {
		return projectRepository.findById(projectId);
	}

	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream()
				.map(project -> new ProjectDTO(project, categoryRepository.findByProjectId(project.getId()),
						technicalRepository.findByProjectId(project.getId())))
				.toList();
	}

	public void incrementDownloadCount(String projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));
		project.setDownloadCount(project.getDownloadCount() + 1);
		projectRepository.save(project);
	}
	
	public List<ProjectDTO> getFilteredProjects(String tech, String field, String language) {
        List<Project> projects = projectRepository.filterProjects(tech, field, language);
        System.out.println("Projects found: " + projects.size());
        System.out.println("tech: [" + tech + "]");
        System.out.println("field: [" + field + "]");
        System.out.println("language: [" + language + "]");
        return projects.stream()
            .map(project -> new ProjectDTO(
                project,
                categoryRepository.findByProjectId(project.getId()), 
                technicalRepository.findByProjectId(project.getId())
            ))
            .collect(Collectors.toList());
    }
}
