package com.aeCoder.project3ae.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IdSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID của bảng
    private Long id;

    @Column(unique = true, nullable = false)
    private String lastId; // Lưu ID cuối cùng

    // Constructors
    public IdSequence() {}

    public IdSequence(String lastId) {
        this.lastId = lastId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}
