package com.example.invproject.repository;

// Update the import to the correct package for Labcategory
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invproject.model.Labcategory;

public interface LabcategoryRepository extends JpaRepository<Labcategory, Long> {
    Optional<Labcategory> findByCategoryName(String categoryName);
}
