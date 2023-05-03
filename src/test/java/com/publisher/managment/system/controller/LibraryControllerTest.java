package com.publisher.managment.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest extends BaseTest {
    @MockBean
    private LibraryService toTest;

    @Test
    public void test_getLibraries_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getLibrariesByStatus(anyBoolean())).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/payments")
                        .param("isDeleted", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getLibraryById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getLibraryById(1)).thenReturn(new LibraryDTO());
        mvc.perform(MockMvcRequestBuilders.get("/libraries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new PaymentDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_addLibrary_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new LibraryDTO()).when(toTest).addLibrary(any());
        mvc.perform(MockMvcRequestBuilders.post("/libraries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new LibraryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateLibrary_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.updateLibrary(new LibraryDTO())).thenReturn(any());
        mvc.perform(MockMvcRequestBuilders.put("/libraries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new LibraryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteLibrary_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).deleteLibraryById(any());
        mvc.perform(MockMvcRequestBuilders.delete("/libraries/{id}", 1))
                .andExpect(status().isOk());
    }
}
