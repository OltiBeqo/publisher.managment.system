package com.publisher.managment.system.repository.queries;

public class Query {
        protected static final String GET_USERS = "select u from User u";
        protected static final String GET_USER_BY_ID = "select u from User u where u.id = :id";
        protected static final String GET_ORDERS = "select o from Order o";
        protected static final String GET_ORDERS_BY_ID = "select o from Order o where o.id = :id";
        protected static final String GET_LIBRARIES = "select l from Library l";
        protected static final String GET_LIBRARY_BY_ID = "select l from Library l where l.id = :id";
        protected static final String GET_CATEGORIES = "select c from Category c";
        protected static final String GET_CATEGORIES_BY_ID = "select c from Category c where c.id = :id";
        protected static final String GET_BOOKS = "select b from Book b";
        protected static final String GET_BOOK_BY_ID = "select b from Book b where b.id = :id";
    }
