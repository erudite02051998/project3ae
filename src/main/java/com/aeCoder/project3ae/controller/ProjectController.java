package com.aeCoder.project3ae.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aeCoder.project3ae.dto.ProjectDTO;
import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.entity.Project;
import com.aeCoder.project3ae.service.CategoryService;
import com.aeCoder.project3ae.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProjectDTO> uploadFile(@RequestPart("file") MultipartFile file,
			@RequestPart("project") String projectJson, @RequestPart("category") String CategoryJson) {

		try {
			// Chuyển JSON từ chuỗi sang Object
			ObjectMapper objectMapper = new ObjectMapper();
			Project project = objectMapper.readValue(projectJson, Project.class);
			Category category = objectMapper.readValue(CategoryJson, Category.class);

			// Đường dẫn thư mục upload
			File uploadDir = new File("uploads/");
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
			}
			
			// ⏩ Bước 1: Lưu Project vào database trước
	        Project savedProject = projectService.createProject(project);	        

	        // Kiểm tra nếu ID vẫn `null`, báo lỗi
	        if (savedProject.getId() == null) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(null);
	        }
			
			// 🔥 Tạo tên file theo ID dự án (ví dụ: "P000012.zip")
	        String newFileName = savedProject.getId() + ".zip";
	        
			// Lưu file lên server
			String filePath = System.getProperty("user.dir") + "/uploads/" + newFileName;
			File destinationFile = new File(filePath);
			file.transferTo(destinationFile);

			// Cập nhật đường dẫn file trong database
			savedProject.setProjectPath(filePath);
			category.setProjectId(savedProject.getId());
			Category saveCategory = categoryService.saveCategory((category));

			return ResponseEntity.ok(new ProjectDTO(savedProject, saveCategory)); // 🔥 Trả về cả Project & Category
		} catch (Exception e) {
			e.printStackTrace(); // 🔥 In lỗi chi tiết ra console để kiểm tra
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(null); // Trả về `null` nếu lỗi xảy ra
		}
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
	    try {
	        // Đường dẫn file trên server
	    	Path filePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
	    	System.out.println("File thực tế: " + filePath.toString()); // 🔥 Kiểm tra log
	    	Resource resource = new UrlResource(filePath.toUri());

	        if (!resource.exists()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                .body(resource);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}


	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable String id) {
		return projectService.getProjectById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
}
