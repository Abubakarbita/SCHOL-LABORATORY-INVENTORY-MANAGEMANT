package com.example.invproject.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invproject.model.School;
import com.example.invproject.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        return schoolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        school.setCreatedAt(LocalDateTime.now());
        // Optionally set the user here if needed
        return schoolRepository.save(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School schoolDetails) {
        return schoolRepository.findById(id).map(school -> {
            school.setSchoolName(schoolDetails.getSchoolName());
            school.setAddress(schoolDetails.getAddress());
            school.setPhone(schoolDetails.getPhone());
            school.setPrincipal(schoolDetails.getPrincipal());
            school.setUploadedAt(LocalDateTime.now());
            return ResponseEntity.ok(schoolRepository.save(school));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
