package com.aeCoder.project3ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.Technical;

@Repository
public interface TechnicalRepository extends JpaRepository<Technical, Long>  {
	List<Technical> findByProjectId(String projectId);
}
