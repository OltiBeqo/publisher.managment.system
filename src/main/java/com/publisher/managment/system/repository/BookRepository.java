package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    List<Book> findByDeleted(boolean isDeleted);
    List<Book> findByCategoryId(Integer id);
}
