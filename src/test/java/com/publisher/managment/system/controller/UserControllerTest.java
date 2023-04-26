package com.publisher.managment.system.controller;

import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    @Disabled
    public void test_registerUser_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDTO()).when(userService).registerUser(any());
        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().isOk());
        //TODO
    }

    @Test
    public void test_registerUser_ko() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new UserDTO()).when(userService).registerUser(any());
        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test_getUsers_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(userService.getUsers()).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void test_getUserById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDTO()).when(userService).getUserById(any());
        mvc.perform(MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getUserById_ko() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(userService.getUserById(Mockito.any())).thenThrow(new ResourceNotFoundException("user not found"));
        mvc.perform(MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Disabled
    public void test_updateUser_ok() {

    }

    @Test
    void testDeleteUserById() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(userService).deleteUserById(Mockito.any());
        mvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
