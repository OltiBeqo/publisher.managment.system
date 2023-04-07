package com.publisher.managment.system.entity.enums;

import com.publisher.managment.system.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public enum OrderStatus {

    IN_PENDING("IN_PROGRESS"), CANCELLED("CANCELLED"), DELIVERED("DELIVERED");

    private String value;

    public static OrderStatus fromValue(String orderStatus){
        return Arrays.asList(OrderStatus.values())
                .stream().filter(r -> r.value.equals(orderStatus))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Order status %s not found",orderStatus)));
    }

    public String getValue() {
        return value;
    }
}

