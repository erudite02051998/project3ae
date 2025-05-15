package com.aeCoder.project3ae.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String projectId; // 🔥 Bình luận thuộc về dự án nào
    private String userId;  // 🔥 Ai đã bình luận
    private String content; // 🔥 Nội dung bình luận
    private LocalDateTime createdAt = LocalDateTime.now(); // 🔥 Thời gian bình luận
    
	public Comment() {}
	
	public Comment(String projectId, String userId, String content, LocalDateTime createdAt) {		
		this.projectId = projectId;
		this.userId = userId;
		this.content = content;
		this.createdAt = createdAt;
	}
    
    
}
