package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Role;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    UserDTO getUserById(Integer id);
    UserDTO updateUser(Integer id, UserDTO userDTO);
    void deleteUserById(Integer id);
    List<UserDTO> getUsersByRole(Role role);
    User getRandomCourier();
}
