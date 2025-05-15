package com.aeCoder.project3ae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Category;
import com.aeCoder.project3ae.repository.CagtegoryRepository;

@Service
public class CategoryService {

	@Autowired
	CagtegoryRepository cagtegoryRepository;
	public Category saveCategory(Category category) {
		return cagtegoryRepository.save(category);
	}
}
