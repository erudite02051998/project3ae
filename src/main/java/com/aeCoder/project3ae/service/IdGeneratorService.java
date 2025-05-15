package com.aeCoder.project3ae.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeCoder.project3ae.entity.IdSequence;
import com.aeCoder.project3ae.repository.IdSequenceRepository;

@Service
public class IdGeneratorService {

    @Autowired
    private IdSequenceRepository idSequenceRepository;

    public String generateNextId() {
        // Lấy ID cuối cùng từ DB
        Optional<IdSequence> lastIdEntry = idSequenceRepository.findTopByOrderByIdDesc();        
        String lastId = lastIdEntry.map(IdSequence::getLastId).orElse("P000000");
        
        // Chuyển phần số thành int, tăng lên 1
        int number = Integer.parseInt(lastId.substring(1)) + 1;
        
        // Định dạng lại ID với padding '0'
        String nextId = String.format("P%06d", number);

        // Lưu ID mới vào DB
        idSequenceRepository.save(new IdSequence(nextId));

        return nextId;
    }
}

