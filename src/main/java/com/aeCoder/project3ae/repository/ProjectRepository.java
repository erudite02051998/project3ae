package com.aeCoder.project3ae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}