package com.aeCoder.project3ae.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

	//Filter theo language, tech, field
	@Query("SELECT p FROM Project p " +
		       "LEFT JOIN Category c ON p.id = c.projectId " +
		       "LEFT JOIN Technical t ON p.id = t.projectId " +
		       "WHERE (:language IS NULL OR c.language LIKE CONCAT('%', :language, '%')) " +
		       "AND (:field IS NULL OR c.field LIKE CONCAT('%', :field, '%')) " +
		       "AND (:tech IS NULL OR t.tech LIKE CONCAT('%', :tech, '%'))")
		List<Project> filterProjects(@Param("tech") String tech, 
		                             @Param("field") String field, 
		                             @Param("language") String language);

}