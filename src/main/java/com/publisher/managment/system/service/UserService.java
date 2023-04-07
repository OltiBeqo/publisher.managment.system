package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerUser(UserDTO req, String userRole);
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    UserDTO getUserById(Integer id);
    UserDTO updateUser(Integer id, UserDTO userDTO);
    void deleteUserById(Integer id);

}
