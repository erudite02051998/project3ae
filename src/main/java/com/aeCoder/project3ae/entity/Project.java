package com.aeCoder.project3ae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Project {
	@Id
    private String id; 			//ID duy nhất của dự án
    private String title; 		//Tên dự án
    private String description; //Mô tả dự án
    private String createdAt; 	//Ngày tạo
    private String userId; 		//ID người tạo dự án
    private String projectPath; //Đường dẫn lưu thư mục project

    public Project() {
	}
       
    public Project(String id, String title, String description, String createdAt, String userId, String projectPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.userId = userId;
        this.projectPath = projectPath;
    }
}
