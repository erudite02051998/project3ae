package com.aeCoder.project3ae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Rating;
import com.aeCoder.project3ae.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;
	
	public Rating addRating(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	public double calculateAverageRating(String projectId) {
	    List<Rating> ratings = ratingRepository.findByProjectId(projectId);
	    return ratings.stream().mapToInt(Rating::getRating).average().orElse(0.0);
	}
	
	public List<Rating> getProjectRatings(String projectId) {
	    return ratingRepository.findByProjectId(projectId);
	}
}
