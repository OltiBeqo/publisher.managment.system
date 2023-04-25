package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.entity.enums.Role;

import java.util.List;

public interface UserService {
    UserDTO registerUser(AuthRequest request);

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUserById(Integer id);

    List<UserDTO> getUsersByRole(Role role);

    UserDTO getRandomCourier();
}
