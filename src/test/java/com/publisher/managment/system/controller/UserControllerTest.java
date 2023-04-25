package com.publisher.managment.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.service.UserService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {

    @MockBean
    private UserService userService;

    public void test_registerUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDTO()).when(userService).registerUser(any());
        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().isOk());
    }

    public void test_registerUser_ko() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new UserDTO()).when(userService).registerUser(any());
        mvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().is4xxClientError());
    }

    public void test_getUsers_ok(){

    }

    public void test_getUserById_ok(){

    }

    public void test_updateUser_ok(){

    }

    public void test_deleteUserById_ok(){

    }

}
