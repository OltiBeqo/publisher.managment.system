package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Category;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.BookMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.CategoryRepository;
import com.publisher.managment.system.service.BookService;
import com.publisher.managment.system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends ExceptionMessage implements BookService {
    @Autowired private BookRepository bookRepository;
    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO) {
        if(bookDTO.getId() != null){
            Book bookExisting = bookRepository.findById(bookDTO.getId()).get();
            BookMapper.toEntityForUpdate(bookExisting, bookDTO);
            return BookMapper.toDto(bookRepository.save(bookExisting));
        }else {
            return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(bookDTO)));
        }
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public BookDTO getBookById(Integer id) {
        return bookRepository.findById(id).map(BookMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, id)));
    }

    @Override
    public BookDTO getBookByTitle(String title){
        return bookRepository.findBookByTitle(title).map(BookMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_TITLE_FOUND, title)));
    }

    @Override
    @Transactional
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, id)));
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntityForUpdate(book, bookDTO)));
    }

    @Override
    @Transactional
    public void deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, id)));
        bookRepository.delete(book);
    }
}
