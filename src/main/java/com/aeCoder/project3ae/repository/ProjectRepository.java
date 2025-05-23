package com.aeCoder.project3ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	@Query(value = "SELECT p.*, c.*, t.* FROM project p " + "LEFT JOIN category c ON p.id = c.project_id "
			+ "LEFT JOIN technical t ON p.id = t.project_id", nativeQuery = true)
	List<Object[]> getProjectDetails();

}