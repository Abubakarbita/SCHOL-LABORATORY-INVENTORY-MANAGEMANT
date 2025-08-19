//package com.example.invproject.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.example.invproject.model.Users;
//import com.example.invproject.repository.UsersRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UsersService {
//
//    private final UsersRepository usersRepository;
//
//    public List<Users> getAllUsers() {
//        return usersRepository.findAll();
//    }
//
//    public Optional<Users> getUserById(Long id) {
//        return usersRepository.findById(id);
//    }
//
//    public Users createUser(Users user) {
//        return usersRepository.save(user);
//    }
//
//    public Users updateUser(Long id, Users updatedUser) {
//        return usersRepository.findById(id).map(user -> {
//            user.setFullName(updatedUser.getFullName());
//            user.setUserName(updatedUser.getUserName());
//            user.setEmail(updatedUser.getEmail());
//            user.setRole(updatedUser.getRole());
//            user.setPassword(updatedUser.getPassword());
//            return usersRepository.save(user);
//        }).orElse(null);
//    }
//
//    public void deleteUser(Long id) {
//        usersRepository.deleteById(id);
//    }
//}
