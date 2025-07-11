package com.aeCoder.project3ae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID của bảng
    private Long id;
	private String language; //Ngôn ngữ lập trình chính
	private String field; 	 //Lĩnh vực của project
	private String projectId;
	
	public Category () {}
	public Category (String language, String field, String projectId) {
		this.language = language;
		this.field = field;
		this.projectId = projectId;
	}
}
