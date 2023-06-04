package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.BookMapper;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.service.BookService;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.search.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends ExceptionMessage implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO) {
        Book bookExisting = bookRepository.findByTitle(bookDTO.getTitle()).orElse(null);
        if (bookExisting != null) {
            BookMapper.toEntityForUpdate(bookExisting, bookDTO);
            return BookMapper.toDto(bookRepository.save(bookExisting));
        } else {
            return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(bookDTO)));
        }
    }

    public Page<BookDTO> getBooksPaginated (Pageable pageable){
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<Book> bookList = bookPage.getContent();
        List<BookDTO> content = bookList.stream().map(BookMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, bookPage.getPageable(), bookPage.getSize());
    }

    public Page<BookDTO> searchBook(SearchRequest request) {
        SearchSpecification<Book> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Book> bookPage = bookRepository.findAll(specification, pageable);
        List<Book> bookList = bookPage.getContent();
        List<BookDTO> content = bookList.stream().map(BookMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, bookPage.getPageable(), bookPage.getSize());
    }

    @Override
    public List<BookDTO> getBooksByStatus(boolean isDeleted) {
        return bookRepository.findByDeleted(isDeleted).stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Integer id) {
        return bookRepository.findById(id).map(BookMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, id)));
    }

    @Override
    public BookDTO getBookByTitle(String title) {
        return bookRepository.findByTitle(title).map(BookMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_TITLE_FOUND, title)));
    }

    @Override
    public List<BookDTO> getBooksByCategoryId(Integer id) {
        return bookRepository.findByCategoryId(id).stream().map(BookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, bookDTO.getId())));
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntityForUpdate(book, bookDTO)));
    }

    @Override
    @Transactional
    public void deleteBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(BOOK_NOT_FOUND, id)));
        bookRepository.delete(book);
    }

}
