package com.publisher.managment.system.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum PaymentMethod {
    CASH("CASH"), BANK_TRANSFER("BANK_TRANSFER"), RECEIVABLE("RECEIVABLE");

    private String value;

    public static PaymentMethod fromValue(String value) {
        return Arrays.asList(PaymentMethod.values()).stream()
                .filter(p -> p.value.equals(value))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Payment method %s not found", value)));
    }

    public String getValue() {
        return value;
    }

}
