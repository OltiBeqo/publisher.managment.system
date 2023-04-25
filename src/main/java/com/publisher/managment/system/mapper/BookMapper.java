package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Category;

import java.time.LocalDateTime;

public class BookMapper {

    public static BookDTO toDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .category(
                        book.getCategory() != null
                                ? CategoryMapper.toDto(book.getCategory())
                                : null
                )
                .deleted(book.isDeleted())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Book toEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .category(
                        bookDTO.getCategory() != null
                                ? CategoryMapper.toEntity(bookDTO.getCategory())
                                : null
                )
                .quantity(bookDTO.getQuantity())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Book toEntityForUpdate(Book book, BookDTO bookDTO) {
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(book.getQuantity() + bookDTO.getQuantity());
        book.setModifiedAt(LocalDateTime.now());
        return book;
    }
}
