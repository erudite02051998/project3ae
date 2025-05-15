package com.aeCoder.project3ae.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeCoder.project3ae.entity.IdSequence;

@Repository
public interface IdSequenceRepository extends JpaRepository<IdSequence, Long> {

    // Tìm bản ghi mới nhất theo ID (sắp xếp giảm dần)
    Optional<IdSequence> findTopByOrderByIdDesc();
}
