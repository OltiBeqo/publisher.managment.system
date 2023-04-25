package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO> getBooksByStatus(boolean isDeleted);

    BookDTO getBookById(Integer id);

    BookDTO getBookByTitle(String title);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBookById(Integer id);
}
