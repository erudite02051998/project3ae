package com.aeCoder.project3ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeCoder.project3ae.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByProjectId(String projectId);
}
