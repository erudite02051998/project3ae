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
public class Technical {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID của bảng
	private Long id;
	private String tech; 	 //Công nghệ sử dụng
	private String projectId;
}
