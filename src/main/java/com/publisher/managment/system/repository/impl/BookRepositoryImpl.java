package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.repository.BookRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class BookRepositoryImpl extends NamedQueries implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Book addBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return entityManager.createNamedQuery(GET_BOOKS, Book.class).getResultList();
    }

    @Override
    public Book getBookById(Integer id) {
        return entityManager.createNamedQuery(GET_BOOK_BY_ID, Book.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Book updateBook(Integer id) {
        Book book = getBookById(id);
        return entityManager.merge(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        Book book = getBookById(id);
        entityManager.remove(book);
    }
}