package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .comment(order.getComment())
                .discount(order.getDiscount())
                .book(order.getBook().stream().map(BookMapper::toDto).collect(Collectors.toList()))
                .totalAmount(order.getBook().stream().map(book -> (book.getPrice() * book.getQuantity()) - order.getDiscount()).mapToDouble(Double::doubleValue).sum())
//                .orderStatus(order.getOrderStatus().getValue())
                .payment(order.getPayment().getPaymentMethod().getValue())
                .createdAt(LocalDate.now())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .comment(orderDTO.getComment())
                .totalAmount(orderDTO.getTotalAmount())
//                .orderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()))
//                .books(orderDTO.getBook() != null ? BookMapper.toDto(orderDTO.getBook().stream().map(BookMapper.toDto())) : null)
                .createdAt(LocalDate.now())
                .build();
    }

    public static Order toEntityForUpdate(Order order, OrderDTO orderDTO) {
//        order.setBooks(orderDTO.getBook().stream().map(bookDTO -> BookMapper.toEntity(bookDTO)).collect(Collectors.toList()));
        order.setComment(order.getComment());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderStatus(OrderStatus.fromValue(orderDTO.getOrderStatus()));
        order.setUser(orderDTO.getUser());
        return order;
    }
}
