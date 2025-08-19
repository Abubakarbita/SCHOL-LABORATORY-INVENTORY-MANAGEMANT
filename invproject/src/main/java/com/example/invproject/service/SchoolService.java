//
//package com.example.invproject.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.example.invproject.model.School;
//import com.example.invproject.repository.SchoolRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class SchoolService {
//
//    private final SchoolRepository schoolRepository;
//
//    public List<School> getAllSchools() {
//        return schoolRepository.findAll();
//    }
//
//    public Optional<School> getSchoolById(Long id) {
//        return schoolRepository.findById(id);
//    }
//
//    public School createSchool(School school) {
//        return schoolRepository.save(school);
//    }
//
//    public School updateSchool(Long id, School updatedSchool) {
//        return schoolRepository.findById(id).map(school -> {
//            school.setSchoolName(updatedSchool.getSchoolName());
//            school.setAddress(updatedSchool.getAddress());
//            school.setPhone(updatedSchool.getPhone());
//            school.setPrincipal(updatedSchool.getPrincipal());
//            return schoolRepository.save(school);
//        }).orElse(null);
//    }
//
//    public void deleteSchool(Long id) {
//        schoolRepository.deleteById(id);
//    }
//}
