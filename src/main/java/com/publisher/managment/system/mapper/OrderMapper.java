package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .library(LibraryMapper.toDto(order.getLibrary()))
                .comment(order.getComment())
                .books(order.getBooks().stream().map(BookMapper::toDto).collect(Collectors.toList()))
                .totalAmount(order.getTotalAmount())
                .payment(order.getPayment().getPaymentMethod().getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .comment(orderDTO.getComment())
                .totalAmount(orderDTO.getTotalAmount())
                .library(LibraryMapper.toEntity(orderDTO.getLibrary()))
                .orderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()))
                .books(orderDTO.getBooks().stream().map(BookMapper::toEntity).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Order toEntityForUpdate(Order order, OrderDTO orderDTO) {
        order.setBooks(orderDTO.getBooks().stream().map(bookDTO -> BookMapper.toEntity(bookDTO)).collect(Collectors.toList()));
        order.setComment(order.getComment());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()));
        order.setUser(orderDTO.getUser());
        return order;
    }
}
