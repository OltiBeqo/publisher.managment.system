package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.mapper.UserMapper;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return UserMapper.toDto(userRepository.createUser(UserMapper.toEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.getUsers().stream().map(user -> UserMapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Integer id) {
            return UserMapper.toDto(userRepository.getUserById(id));
        }

    @Override
    public UserDTO updateUser(Integer id) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

    }
}
