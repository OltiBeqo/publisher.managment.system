package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.mapper.BookMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO) {
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(bookDTO)));
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream().map(book -> BookMapper.toDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Integer id) {
        return bookRepository.findById(id).map(book -> BookMapper.toDto(book)).orElseThrow(()-> new RuntimeException());
    }

    @Override
    @Transactional
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntityForUpdate(book, bookDTO)));
    }

    @Override
    @Transactional
    public void deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException());
        bookRepository.delete(book);
    }
}
