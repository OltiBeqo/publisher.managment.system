package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Category;

public class BookMapper {

    public static BookDTO toDto(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .category(book.getCategory()!=null?CategoryMapper.toDto(book.getCategory()):null)
                .build();
    }
    public static Book toEntity(BookDTO bookDTO, CategoryDTO categoryDTO){
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .category(CategoryMapper.toEntity(categoryDTO))
                .quantity(bookDTO.getQuantity())
                .build();
    }
    public static Book toEntityForUpdate(Book book, BookDTO bookDTO){
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());
        return book;
    }
}
