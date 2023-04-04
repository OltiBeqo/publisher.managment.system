package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.mapper.UserMapper;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ExceptionMessage implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
        if (user != null) {
            throw new BadRequestException(String.format(USERNAME_EXISTS, User.class.getSimpleName(), "username", userDTO.getUsername()));
        }
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> UserMapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id).map(user -> UserMapper.toDto(user))
                .orElseThrow(()-> new BadRequestException(String.format(NOT_FOUND, User.class.getSimpleName(), id)));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new BadRequestException(String.format(NOT_FOUND, User.class.getSimpleName(), id)));
        return UserMapper.toDto(userRepository.save(UserMapper.toEntityForUpdate(user, userDTO)));
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new BadRequestException(String.format(NOT_FOUND, User.class.getSimpleName(), id)));
        userRepository.delete(user);
    }
}
