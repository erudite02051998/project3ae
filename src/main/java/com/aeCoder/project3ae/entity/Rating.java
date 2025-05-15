package com.aeCoder.project3ae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID của bảng
    private Long id;
	private String projectId;
	private String userId;
	private int rating;
	
	public Rating() {}
	public Rating (String projectId, String userId, int rating) {
		this.projectId = projectId;
		this.userId = userId;
		this.rating = rating;
	}
}
