package com.example.invproject.model;

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
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;

    private String userName;

    // Hapa password haifichwi (hapana @JsonIgnore), ili ipokelewe kwenye login request
    private String password;

    private String email;

    private String role;

    @OneToMany(mappedBy = "users")
    @JsonIgnore  // Hii inalinda loop za serialization kwa related entities
    private List<Item> createdItems;

    @ManyToOne
@JoinColumn(name = "school_id")
private School school;


}
