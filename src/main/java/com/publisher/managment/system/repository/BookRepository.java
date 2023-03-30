package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Book;

import java.util.List;

public interface BookRepository {

    Book addBook(Book book);
    List<Book> getBooks();
    Book getBookById(Integer id);
    Book updateBook(Integer id);
    void deleteBookById(Integer id);
}
