package com.example.invproject.Dto;

import lombok.Data;

@Data
public class AddUsersDto {
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String role;
    private Long schoolId;
}
