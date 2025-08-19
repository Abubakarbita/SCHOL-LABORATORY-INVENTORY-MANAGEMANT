package com.example.invproject.controller;

import com.example.invproject.Dto.AddUsersDto;
import com.example.invproject.model.School;
import com.example.invproject.model.Users;
import com.example.invproject.repository.SchoolRepository;
import com.example.invproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository usersRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    private  UserRepository usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return usersRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AddUsersDto req) {
        try {

            if (usersRepository.findByUserName(req.getUserName()).isPresent()) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            Users users = new Users();
            users.setFullName(req.getFullName());
            users.setUserName(req.getUserName());
            users.setEmail(req.getEmail());
            users.setPassword(passwordEncoder.encode(req.getPassword()));
            users.setRole(req.getRole());

            // Associate school if Lab Technician and schoolId is provided
            if ("Lab Technician".equals(req.getRole()) && req.getSchoolId() != null) {
                School school = schoolRepository.findById(req.getSchoolId())
                        .orElseThrow(() -> new RuntimeException("School not found"));
                users.setSchool(school);
            }

            Users savedUser = usersRepository.save(users);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to create user: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody AddUsersDto userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setFullName(userDetails.getFullName());
            user.setUserName(userDetails.getUserName());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            return ResponseEntity.ok(usersRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
