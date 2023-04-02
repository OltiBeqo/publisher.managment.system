package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .book(order.getBooks().stream().map(book -> BookMapper.toDto(book)).collect(Collectors.toList()))
                .createdAt(LocalDate.now())
                .build();
    }
    public static Order toEntity(OrderDTO orderDTO){
        return Order.builder()
                .id(orderDTO.getId())
                .totalAmount(orderDTO.getTotalAmount())
                .books(orderDTO.getBook().stream().map(bookDTO -> BookMapper.toEntity(bookDTO)).collect(Collectors.toList()))
                .createdAt(LocalDate.now())
                .build();
    }
    public static Order toEntityForUpdate(Order order, OrderDTO orderDTO){
        order.setBooks(orderDTO.getBook().stream().map(bookDTO -> BookMapper.toEntity(bookDTO)).collect(Collectors.toList()));
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setUser(orderDTO.getUser());
        return order;
    }
}
