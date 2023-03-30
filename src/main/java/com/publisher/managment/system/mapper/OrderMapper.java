package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderMapper {
    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(LocalDate.now())
                .build();
    }
    public static Order toEntity(OrderDTO orderDTO){
        return Order.builder()
                .id(orderDTO.getId())
                .totalAmount(orderDTO.getTotalAmount())
                .createdAt(LocalDate.now())
                .build();
    }
}
