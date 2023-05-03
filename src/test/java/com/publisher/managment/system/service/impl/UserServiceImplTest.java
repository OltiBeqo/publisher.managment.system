package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Gender;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService toTest;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder encoder;

    @Test
    @Disabled
    public void test_registerUser_ok(){

    }

    @Test
    @Disabled
    public void test_registerUser_ko(){

    }

    @Test
    public void test_getUsers_ok(){
        List<UserDTO> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(toTest.getUsers().isEmpty());
        verify(userRepository).findAll();

        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstname("test");
        user.setLastname("test");
        user.setGender(Gender.MALE);
        user.setRole(Role.ADMIN);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userList.size());
        verify(userRepository).findAll();
    }

    @Test
    public void test_getUserById_ok(){
        User user = new User();
        user.setId(1);
        user.setFirstname("test");
        user.setLastname("test");
        user.setUsername("test");
        user.setPassword("test");
        user.setGender(Gender.MALE);
        user.setRole(Role.ADMIN);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());

        Optional<User> result = Optional.of(user);
        when(userRepository.findById(Mockito.anyInt())).thenReturn(result);

        UserDTO userDTO = toTest.getUserById(1);
        assertEquals(1, userDTO.getId().intValue());
        assertEquals("test", userDTO.getFirstname());
        assertEquals("test", userDTO.getLastname());
        assertEquals("test", userDTO.getUsername());
        assertEquals("ADMIN", userDTO.getRole());
        assertEquals("MALE", userDTO.getGender());
        verify(userRepository).findById(Mockito.anyInt());

    }

    @Test
    public void test_getUserById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("user not found"))
                .when(userRepository).findById(Mockito.anyInt());
        Throwable throwable = assertThrows(Throwable.class,()-> toTest.getUserById(1));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    @Disabled
    public void test_updateUser_ok(){

    }

    @Test
    public void test_deleteUserById_ok(){
        User user = new User();
        Optional<User> result = Optional.of(user);
        doNothing().when(userRepository).delete(Mockito.<User>any());
        when(userRepository.findById(Mockito.anyInt())).thenReturn(result);
        toTest.deleteUserById(1);
        verify(userRepository).findById(Mockito.any());
        verify(userRepository).delete(Mockito.<User>any());
    }

    @Test
    @Disabled
    public void test_getRandomCourier_ok(){

    }
}
