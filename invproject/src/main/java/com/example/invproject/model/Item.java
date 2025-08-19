package com.example.invproject.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String unit;
    private String status;
    private String storage;
    private Integer quantity;

    private LocalDateTime createdAt;
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "createdItems", "school"})
    private Users users;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonIgnoreProperties("users")
    private School school;
}
