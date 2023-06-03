package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO> getBooksByStatus(boolean isDeleted);

    BookDTO getBookById(Integer id);

    BookDTO getBookByTitle(String title);

    BookDTO updateBook(BookDTO bookDTO);

    void deleteBookById(Integer id);

    List<BookDTO> getBooksByCategoryId(Integer id);

    Page<BookDTO> getBooksPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<BookDTO> searchBook(SearchRequest request);

}
