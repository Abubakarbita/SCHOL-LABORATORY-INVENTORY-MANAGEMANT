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

import com.example.invproject.model.Labcategory;
import com.example.invproject.repository.LabcategoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class LabcategoryController {

    private final LabcategoryRepository labcategoryRepository;

    @GetMapping
    public List<Labcategory> getAllCategories() {
        return labcategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Labcategory> getCategoryById(@PathVariable Long id) {
        return labcategoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Labcategory createCategory(@RequestBody Labcategory category) {

        return labcategoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (labcategoryRepository.existsById(id)) {
            labcategoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
