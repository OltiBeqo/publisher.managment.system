package com.publisher.managment.system.repository.impl.queries;
public class NamedQueries {

    protected static final String GET_USERS = "select u from Users u";
    protected static final String GET_USER_BY_ID = "select u from Users u where u.id = :id";
    protected static final String GET_ROLES = "select r from Roles r";
    protected static final String GET_ROLES_BY_ID = "select r from Roles r where r.id = :id";
    protected static final String GET_ORDERS = "select o from Orders o";
    protected static final String GET_ORDERS_BY_ID = "select o from Orders o where o.id = :id";
    protected static final String GET_LIBRARIES = "select l from Libraries l";
    protected static final String GET_LIBRARY_BY_ID = "select l from Libraries l where l.id = :id";
    protected static final String GET_CATEGORIES = "select c from Categories c";
    protected static final String GET_CATEGORIES_BY_ID = "select c from Categories c where c.id = :id";
    protected static final String GET_BOOKS = "select b from Books b";
    protected static final String GET_BOOK_BY_ID = "select b from Books b where b.id = :id";
}
