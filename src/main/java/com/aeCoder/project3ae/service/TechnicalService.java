package com.aeCoder.project3ae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.Technical;
import com.aeCoder.project3ae.repository.TechnicalRepository;

@Service
public class TechnicalService {
	@Autowired
	TechnicalRepository technicalRepository;

	public List<Technical> saveTechnicals(List<Technical> technicals) {
		return technicalRepository.saveAll(technicals);
	}

	public List<Technical> getTechnicalsByProjectId(String projectId) {
		return technicalRepository.findByProjectId(projectId);
	}
}
