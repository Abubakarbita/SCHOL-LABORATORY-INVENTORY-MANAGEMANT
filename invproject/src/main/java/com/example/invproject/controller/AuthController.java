package com.example.invproject.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.invproject.model.School;
import com.example.invproject.model.Users;
import com.example.invproject.repository.SchoolRepository;
import com.example.invproject.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Signup
    @PostMapping("/signup")
    public String signup(@RequestBody Users user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "Username already exists!";
        }

        if (user.getSchool() != null && user.getSchool().getSchoolId() != null) {
            Long schoolId = user.getSchool().getSchoolId();
            School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with ID: " + schoolId));
            user.setSchool(school);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "Signup successful!";
    }

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginUser, HttpSession session) {
        Optional<Users> userOptional = userRepository.findByUserName(loginUser.getUserName());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("role", user.getRole());

                if (user.getSchool() != null) {
                    session.setAttribute("schoolId", user.getSchool().getSchoolId());
                }

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("userId", user.getUserId());
                response.put("userName", user.getUserName());
                response.put("role", user.getRole());
                response.put("schoolId", user.getSchool() != null ? user.getSchool().getSchoolId() : null);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid password!"));
            }
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "User not found!"));
        }
    }

    // ✅ Logout
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully.";
    }

    // ✅ (Optional) Admin Only Section
    @GetMapping("/admin-section")
    public String adminOnly(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if ("Admin".equalsIgnoreCase(role)) {
            return "Welcome to the admin section.";
        } else {
            return "Access denied. Admins only.";
        }
    }
}
