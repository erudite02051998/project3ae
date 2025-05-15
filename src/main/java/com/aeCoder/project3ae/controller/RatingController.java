package com.aeCoder.project3ae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeCoder.project3ae.entity.Rating;
import com.aeCoder.project3ae.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.addRating(rating));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable String projectId) {
        return ResponseEntity.ok(ratingService.getProjectRatings(projectId));
    }
    
    @GetMapping("/project/Average/{projectId}")
    public ResponseEntity<Double> getAverageRatings(@PathVariable String projectId) {
        return ResponseEntity.ok(ratingService.calculateAverageRating(projectId));
    }
}

	