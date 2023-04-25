package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Gender;
import com.publisher.managment.system.entity.enums.Role;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .gender(user.getGender().getValue())
                .role(user.getRole().getValue())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .gender(Gender.fromValue(userDTO.getGender()))
                .role(Role.fromValue(userDTO.getRole()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static User toEntityForUpdate(User user, UserDTO userDTO) {
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setRole(Role.fromValue(userDTO.getRole()));
        user.setModifiedAt(LocalDateTime.now());
        return user;
    }

    public static User toRegister(AuthRequest request) {
        return User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(request.getPassword())
                .gender(Gender.fromValue(request.getGender()))
                .role(request.getRole() != null ? Role.fromValue(request.getRole()) : Role.EMPLOYEE)
                .build();
    }

}
