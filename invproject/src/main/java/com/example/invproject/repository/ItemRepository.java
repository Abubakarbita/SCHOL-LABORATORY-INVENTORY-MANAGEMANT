package com.example.invproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invproject.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySchool_SchoolId(Long schoolId);

    List<Item> findBySchoolSchoolId(Long schoolId);
}
