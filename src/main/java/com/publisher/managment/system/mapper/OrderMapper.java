package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .library(order.getLibrary() != null
                        ? LibraryMapper.toDto(order.getLibrary())
                        : null
                )
                .comment(order.getComment())
                .user(UserMapper.toDto(order.getUser()))
                .courier(UserMapper.toDto(order.getCourier()))
                .orderStatus(order.getOrderStatus().getValue())
                .books(order.getBooks().stream().map(BookMapper::toDto).collect(Collectors.toList()))
                .totalAmount(order.getTotalAmount())
                .deleted(order.isDeleted())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .comment(orderDTO.getComment())
                .user(UserMapper.toEntity(orderDTO.getUser()))
                .courier(UserMapper.toEntity(orderDTO.getCourier()))
                .discount(orderDTO.getDiscount())
                .totalAmount(orderDTO.getTotalAmount())
                .library(orderDTO.getLibrary() != null
                        ? LibraryMapper.toEntity(orderDTO.getLibrary())
                        : null
                )
                .orderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()))
                .books(orderDTO.getBooks().stream().map(BookMapper::toEntity).collect(Collectors.toList()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Order toEntityForUpdate(Order order, OrderDTO orderDTO) {
        order.setBooks(orderDTO.getBooks().stream().map(BookMapper::toEntity).collect(Collectors.toList()));
        order.setComment(orderDTO.getComment());
        order.setOrderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()));
        order.setModifiedAt(LocalDateTime.now());
        return order;
    }
}
