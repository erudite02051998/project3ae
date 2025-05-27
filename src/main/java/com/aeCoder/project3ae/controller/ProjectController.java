package com.aeCoder.project3ae.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aeCoder.project3ae.dto.ProjectDTO;
import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.entity.Project;
import com.aeCoder.project3ae.entity.Technical;
import com.aeCoder.project3ae.service.CategoryService;
import com.aeCoder.project3ae.service.ProjectService;
import com.aeCoder.project3ae.service.TechnicalService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TechnicalService technicalService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProjectDTO> uploadFile(
			@RequestPart("file") MultipartFile file,
			@RequestPart("project") String projectJson, 
			@RequestPart("categories") String categoriesJson,
			@RequestPart("technicals") String technicalsJson) {

		try {
			// Chuyển JSON từ chuỗi sang Object
			ObjectMapper objectMapper = new ObjectMapper();
			Project project = objectMapper.readValue(projectJson, Project.class);
			List<Category> categories = objectMapper.readValue(categoriesJson, new TypeReference<List<Category>>() {
			});
			List<Technical> technicals = objectMapper.readValue(technicalsJson, new TypeReference<List<Technical>>() {
			});

			// 🔥 Kiểm tra log
			System.out.println("Categories received: " + categories);
			System.out.println("Technicals received: " + technicals);

			// ⏩ Bước 1: Lưu Project vào database trước
			Project savedProject = projectService.createProject(project);

			// Kiểm tra nếu ID vẫn `null`, báo lỗi
			if (savedProject.getId() == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}

			// 🔥 Tạo tên file theo ID dự án
			String newFileName = savedProject.getId() + ".zip";
			String filePath = System.getProperty("user.dir") + "/uploads/" + newFileName;
			file.transferTo(new File(filePath));

			// Cập nhật đường dẫn file
			savedProject.setProjectPath(filePath);

			// 🔥 Gán Project
			categories.forEach(category -> category.setProjectId(savedProject.getId()));
			technicals.forEach(technical -> technical.setProjectId(savedProject.getId()));


			// Lưu danh sách Category và Technical
			List<Category> savedCategories = categoryService.saveCategories(categories);
			List<Technical> savedTechnicals = technicalService.saveTechnicals(technicals);

			// 🔥 Trả về DTO chứa đầy đủ danh sách
			return ResponseEntity.ok(new ProjectDTO(savedProject, savedCategories, savedTechnicals));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam String projectId, @RequestParam String fileName) {
		try {
			Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
			System.out.println("File thực tế: " + filePath.toString()); // 🔥 Kiểm tra log
			Resource resource = new UrlResource(filePath.toUri());

			if (!resource.exists()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			// Tăng số lượt tải
			projectService.incrementDownloadCount(projectId);

			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
					.body(resource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable String id) {
		Optional<Project> project = projectService.getProjectById(id);
		List<Category> listCategory = categoryService.getCategoriesByProjectId(id);
		List<Technical> listTechnical = technicalService.getTechnicalsByProjectId(id);
		return ResponseEntity.ok(new ProjectDTO(project.get(), listCategory, listTechnical));
	}

	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getAllProjects() {
	    List<ProjectDTO> projects = projectService.getAllProjects();
	    
	    // Chuyển đổi sang DTO
	    List<ProjectDTO> projectDTOs = projects.stream()
	        .map(project -> new ProjectDTO(
	            project.getProject(),
	            categoryService.getCategoriesByProjectId(project.getProject().getId()),
	            technicalService.getTechnicalsByProjectId(project.getProject().getId())
	        ))
	        .toList();

	    return ResponseEntity.ok(projectDTOs);
	}
}
