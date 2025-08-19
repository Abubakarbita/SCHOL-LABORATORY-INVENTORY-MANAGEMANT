//package com.example.invproject.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.example.invproject.model.Subject;
//import com.example.invproject.repository.SubjectRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class SubjectService {
//
//    private final SubjectRepository subjectRepository;
//
//    public List<Subject> getAllSubjects() {
//        return subjectRepository.findAll();
//    }
//
//    public Optional<Subject> getSubjectById(Long id) {
//        return subjectRepository.findById(id);
//    }
//
//    public Subject createSubject(Subject subject) {
//        return subjectRepository.save(subject);
//    }
//
//    public Subject updateSubject(Long id, Subject updatedSubject) {
//        return subjectRepository.findById(id).map(subject -> {
//            subject.setSubjectName(updatedSubject.getSubjectName());
//            return subjectRepository.save(subject);
//        }).orElse(null);
//    }
//
//    public void deleteSubject(Long id) {
//        subjectRepository.deleteById(id);
//    }
//}
