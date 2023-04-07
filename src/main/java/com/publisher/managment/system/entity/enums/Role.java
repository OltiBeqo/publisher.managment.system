package com.publisher.managment.system.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public enum Role {
    ADMIN("Admin"), EMPLOYEE("Employee"), COURIER("Courier");

    private String value;
    public static Role fromValue(String role){
        return Arrays.asList(Role.values()).stream()
                .filter(r -> r.value.equals(role))
                .findFirst().orElseThrow(()-> new RuntimeException(String.format("Role %s not found", role)));
    }
    public String getValue() {
        return value;
    }

}
