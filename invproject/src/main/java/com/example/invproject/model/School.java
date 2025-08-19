package com.example.invproject.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String schoolName;
    private String address;
    private String phone;
    private String principal;

    private LocalDateTime createdAt;
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;

    @OneToMany(mappedBy = "school")
    @JsonIgnore  // ili kuzuia cyclical serialization
    private List<Subject> subjects;
}
