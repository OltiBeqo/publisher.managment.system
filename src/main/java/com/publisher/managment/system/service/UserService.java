package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.enums.Role;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    UserDTO getUserByUsername(String username);

    UserDTO updateUser(Integer id, UserDTO userDTO);

    void deleteUserById(Integer id);

    List<UserDTO> getUsersByRole(Role role);

    UserDTO getRandomCourier();
}
