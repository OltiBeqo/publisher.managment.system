package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.search.SearchSpecification;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.UserMapper;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ExceptionMessage implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDTO> getUsersPaginated(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> userList = userPage.getContent();
        List<UserDTO> content = userList.stream().map(UserMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, userPage.getPageable(), userPage.getSize());
    }

    @Override
    public Page<UserDTO> searchUser(SearchRequest request) {
        SearchSpecification<User> specification = new SearchSpecification<>(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<User> userPage = userRepository.findAll(specification, pageable);
        List<User> userList = userPage.getContent();
        List<UserDTO> content = userList.stream().map(UserMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, userPage.getPageable(), userPage.getSize());
    }

    @Override
    @Transactional
    public UserDTO registerUser(AuthRequest request) {

        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            throw new BadRequestException(String.format(USERNAME_EXISTS, request.getUsername()));
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return UserMapper.toDto(userRepository.save(UserMapper.toRegister(request)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format(USERNAME_NOT_FOUND, username)));
    }


    @Override
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, userDTO.getId())));
        return UserMapper.toDto(userRepository.save(UserMapper.toEntityForUpdate(user, userDTO)));
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findAllByRole(role)
                .stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getRandomCourier() {
        return UserMapper.toDto(userRepository.findAllByRole(Role.fromValue(Role.COURIER.getValue())).stream()
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(String.format(COURIER_NOT_FOUND))));
    }

}
