//
//package com.example.invproject.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.example.invproject.model.Labcategory;
//import com.example.invproject.repository.LabcategoryRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class LabcategoryService {
//
//    private final LabcategoryRepository labcategoryRepository;
//
//    public List<Labcategory> getAllCategories() {
//        return labcategoryRepository.findAll();
//    }
//
//    public Optional<Labcategory> getCategoryById(Long id) {
//        return labcategoryRepository.findById(id);
//    }
//
//    public Labcategory createCategory(Labcategory category) {
//        return labcategoryRepository.save(category);
//    }
//
//    public Labcategory updateCategory(Long id, Labcategory updatedCategory) {
//        return labcategoryRepository.findById(id).map(category -> {
//            category.setCategoryName(updatedCategory.getCategoryName());
//            category.setDescription(updatedCategory.getDescription());
//            return labcategoryRepository.save(category);
//        }).orElse(null);
//    }
//
//    public void deleteCategory(Long id) {
//        labcategoryRepository.deleteById(id);
//    }
//}
