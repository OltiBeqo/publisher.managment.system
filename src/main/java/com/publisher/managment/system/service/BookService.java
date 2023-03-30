package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.entity.Book;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    List<BookDTO> getBooks();
    BookDTO getBookById(Integer id);
    BookDTO updateBook(Integer id);
    void deleteBookById(Integer id);
}
