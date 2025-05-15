package com.aeCoder.project3ae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.Category;

@Repository
public interface CagtegoryRepository extends JpaRepository<Category, Long> {

}
