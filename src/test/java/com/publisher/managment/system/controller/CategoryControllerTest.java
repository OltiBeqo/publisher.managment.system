package com.publisher.managment.system.controller;

import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.service.CategoryService;
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
public class CategoryControllerTest extends BaseTest {
    @MockBean
    private CategoryService toTest;

    @Test
    public void test_getCategories_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getCategories()).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getCategoryById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getCategoryById(1)).thenReturn(new CategoryDTO());
        mvc.perform(MockMvcRequestBuilders.get("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CategoryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_addCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new CategoryDTO()).when(toTest).addCategory(any());
        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CategoryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.updateCategory(new CategoryDTO())).thenReturn(any());
        mvc.perform(MockMvcRequestBuilders.put("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CategoryDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteCategoryById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).deleteCategoryById(any());
        mvc.perform(MockMvcRequestBuilders.delete("/categories/{id}", 1))
                .andExpect(status().isOk());
    }
}
