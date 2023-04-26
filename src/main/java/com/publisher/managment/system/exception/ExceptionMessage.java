package com.publisher.managment.system.exception;

public class ExceptionMessage {
    protected static final String USER_NOT_FOUND = "User with id %s not found";
    protected static final String USERNAME_NOT_FOUND = "User with username : %s, not found";
    protected static final String USERNAME_EXISTS = "User with username %s already exists";
    protected static final String BOOK_TITLE_FOUND = "Book with title %s not found";
    protected static final String BOOK_NOT_FOUND = "Book with id %d not found";
    protected static final String ORDER_NOT_FOUND = "Order with id %s not found";
    protected static final String LIBRARY_NOT_FOUND = "Library with id %s not found";
    protected static final String CATEGORY_NOT_FOUND = "Category with id %s not found";
    protected static final String QUANTITY_NOT_AVAILABLE = "Quantity %s not available";
    protected static final String PAYMENT_NOT_FOUND = "Payment with id %s not found";
    protected static final String COURIER_NOT_FOUND = "Courier not found";
    protected static final String ORDER_ALREADY_CANCELLED = "Order %s is already cancelled and cannot be updated, please create a new one";
    protected static final String ORDER_NOT_COMPLETED = "Order with id %s is in status %s, cannot be added a payment";
    protected static final String PAYMENT_EXISTS = "Payment for order id %s already exists";
    protected static final String CATEGORY_EXISTS = "Category with name %s already exists";
    protected static final String LIBRARY_EXISTS = "Library store : %s already exists";
}
