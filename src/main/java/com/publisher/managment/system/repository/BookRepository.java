package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.projections.BookSummary;
import com.publisher.managment.system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByTitle(String title);

    List<Book> findByDeleted(boolean isDeleted);

    List<Book> findByCategoryId(Integer id);

}

