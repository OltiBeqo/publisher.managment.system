package com.publisher.managment.system.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.entity.enums.Gender;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.getUsers().isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers2() {
        User user = new User();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setFirstname("Jane");
        user.setGender(Gender.MALE);
        user.setId(1);
        user.setLastname("Doe");
        user.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setRole(Role.ADMIN);
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.getUsers().size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers3() {
        User user = new User();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setFirstname("Jane");
        user.setGender(Gender.MALE);
        user.setId(1);
        user.setLastname("Doe");
        user.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setRole(Role.ADMIN);
        user.setUsername("janedoe");

        User user2 = new User();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setFirstname("John");
        user2.setGender(Gender.FEMALE);
        user2.setId(2);
        user2.setLastname("Smith");
        user2.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setOrders(new ArrayList<>());
        user2.setPassword("Password");
        user2.setRole(Role.EMPLOYEE);
        user2.setUsername("Username");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(2, userServiceImpl.getUsers().size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers4() {
        when(userRepository.findAll()).thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> userServiceImpl.getUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers5() {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.ADMIN);
        when(user.getGender()).thenReturn(Gender.MALE);
        when(user.getId()).thenReturn(1);
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(user).setFirstname(Mockito.<String>any());
        doNothing().when(user).setGender(Mockito.<Gender>any());
        doNothing().when(user).setId(Mockito.<Integer>any());
        doNothing().when(user).setLastname(Mockito.<String>any());
        doNothing().when(user).setModifiedAt(Mockito.<LocalDateTime>any());
        doNothing().when(user).setOrders(Mockito.<List<Order>>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setFirstname("Jane");
        user.setGender(Gender.MALE);
        user.setId(1);
        user.setLastname("Doe");
        user.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setRole(Role.ADMIN);
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.getUsers().size());
        verify(userRepository).findAll();
        verify(user).getGender();
        verify(user).getRole();
        verify(user).getId();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(user).getUsername();
        verify(user).setCreatedAt(Mockito.<LocalDateTime>any());
        verify(user).setFirstname(Mockito.<String>any());
        verify(user).setGender(Mockito.<Gender>any());
        verify(user).setId(Mockito.<Integer>any());
        verify(user).setLastname(Mockito.<String>any());
        verify(user).setModifiedAt(Mockito.<LocalDateTime>any());
        verify(user).setOrders(Mockito.<List<Order>>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRole(Mockito.<Role>any());
        verify(user).setUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsers6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.publisher.managment.system.exception.BadRequestException: An error occurred
        //       at com.publisher.managment.system.mapper.UserMapper.toDto(UserMapper.java:19)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1384)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
        //       at com.publisher.managment.system.service.impl.UserServiceImpl.getUsers(UserServiceImpl.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        when(user.getRole()).thenThrow(new BadRequestException("An error occurred"));
        when(user.getGender()).thenReturn(Gender.MALE);
        when(user.getId()).thenReturn(1);
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        when(user.getUsername()).thenReturn("janedoe");
        doNothing().when(user).setCreatedAt(Mockito.<LocalDateTime>any());
        doNothing().when(user).setFirstname(Mockito.<String>any());
        doNothing().when(user).setGender(Mockito.<Gender>any());
        doNothing().when(user).setId(Mockito.<Integer>any());
        doNothing().when(user).setLastname(Mockito.<String>any());
        doNothing().when(user).setModifiedAt(Mockito.<LocalDateTime>any());
        doNothing().when(user).setOrders(Mockito.<List<Order>>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setFirstname("Jane");
        user.setGender(Gender.MALE);
        user.setId(1);
        user.setLastname("Doe");
        user.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setRole(Role.ADMIN);
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        userServiceImpl.getUsers();
    }
}

