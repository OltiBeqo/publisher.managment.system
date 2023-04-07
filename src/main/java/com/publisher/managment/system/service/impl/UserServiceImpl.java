package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.UserMapper;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserServiceImpl extends ExceptionMessage implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO registerUser(UserDTO userDTO, String userRole) {
        User user = UserMapper.toEntity(userDTO);
        user.setRole(userRole!=null? Role.fromValue(userRole):Role.ADMIN);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
    }
    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
        if (user != null) {
            throw new ResourceNotFoundException(String.format(USERNAME_EXISTS, User.class.getSimpleName(), "username", userDTO.getUsername()));
        }
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id).map(UserMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND, User.class.getSimpleName(), id)));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND, User.class.getSimpleName(), id)));
        return UserMapper.toDto(userRepository.save(UserMapper.toEntityForUpdate(user, userDTO)));
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND, User.class.getSimpleName(), id)));
        userRepository.delete(user);
    }

}
