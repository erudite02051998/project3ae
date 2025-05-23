package com.aeCoder.project3ae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	public List<Category> saveCategories(List<Category> categories) {
	    return categoryRepository.saveAll(categories);
	}
	
	public List<Category> getCategoriesByProjectId(String projectId) {
        return categoryRepository.findByProjectId(projectId);
    }
}
