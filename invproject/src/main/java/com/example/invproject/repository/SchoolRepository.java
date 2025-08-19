package com.example.invproject.repository;

// Update the import below to the correct package where School.java is located
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invproject.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findBySchoolName(String schoolName);
}
