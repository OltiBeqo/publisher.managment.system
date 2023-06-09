package com.publisher.managment.system.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), EMPLOYEE("EMPLOYEE"), COURIER("COURIER");

    private String value;

    public static Role fromValue(String value) {
        return Arrays.asList(Role.values()).stream()
                .filter(r -> r.value.equals(value))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Role %s not found", value)));
    }

    public String getValue() {
        return value;
    }

}
