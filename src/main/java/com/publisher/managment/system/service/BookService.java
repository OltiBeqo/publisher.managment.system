package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO, CategoryDTO categoryDTO);
    List<BookDTO> getBooks();
    BookDTO getBookById(Integer id);
    BookDTO getBookByTitle(String title);
    BookDTO updateBook(Integer id, BookDTO bookDTO);
    void deleteBookById(Integer id);
}
