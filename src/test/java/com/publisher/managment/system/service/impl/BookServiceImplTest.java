package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Category;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService toTest;

    public void test_addBook_ok() {
        //TODO
    }

    @Test
    public void test_getBooks_ok() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        category.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Book book = new Book();
        book.setId(1);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPrice(10.0d);
        book.setQuantity(1);
        book.setCategory(category);
        book.setDeleted(false);
        book.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookRepository.findByDeleted(anyBoolean())).thenReturn(bookList);
        assertEquals(1, toTest.getBooksByStatus(false).size());
        verify(bookRepository).findByDeleted(anyBoolean());
    }

    @Test
    public void test_getBookById_ok() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        category.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Book book = new Book();
        book.setId(1);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPrice(10.0d);
        book.setQuantity(1);
        book.setCategory(category);
        book.setDeleted(false);
        book.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        when(bookRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(book));
    }

    @Test
    public void test_getBookById_ko() {
        when(bookRepository.findAll()).thenThrow(new ResourceNotFoundException("not found"));
        assertThrows(ResourceNotFoundException.class, () -> toTest.getBookById(anyInt()));

    }


    public void test_updateBook_ok() {
        //TODO
    }

    @Test
    public void test_deleteBook_ok() {
        when(bookRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Book()));
        doNothing().when(bookRepository).delete(Mockito.<Book>any());
        toTest.deleteBookById(anyInt());
    }

}
