package com.publisher.managment.system.controller;

import com.publisher.managment.system.BaseTest;
import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest extends BaseTest {

    @MockBean
    private BookService toTest;

    @Test
    void test_getBooks_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getBooksByStatus(anyBoolean())).thenReturn(new ArrayList<>());
        mvc.perform(MockMvcRequestBuilders.get("/books")
                        .param("isDeleted", String.valueOf(false))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getBookById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getBookById(anyInt())).thenReturn(new BookDTO());
        mvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getBookByTitle_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        when(toTest.getBookByTitle(any())).thenReturn(new BookDTO());
        mvc.perform(MockMvcRequestBuilders.get("/books/find/{title}", "title")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BookDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_addBook_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new BookDTO()).when(toTest).addBook(any());
        mvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BookDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_updateBook_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new BookDTO()).when(toTest).updateBook(any());
        mvc.perform(MockMvcRequestBuilders.put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BookDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteBookById_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doNothing().when(toTest).deleteBookById(any());
        mvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1))
                .andExpect(status().isOk());
    }
}
