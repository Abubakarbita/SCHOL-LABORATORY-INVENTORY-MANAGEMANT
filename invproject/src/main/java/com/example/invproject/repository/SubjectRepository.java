package com.example.invproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invproject.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectName(String subjectName);
}
