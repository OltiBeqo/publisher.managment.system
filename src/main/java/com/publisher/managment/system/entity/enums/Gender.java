package com.publisher.managment.system.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String value;
    public static Gender fromValue(String gender){
        return Arrays.asList(Gender.values()).stream()
                .filter(g -> g.value.equals(gender))
                .findFirst().orElseThrow(()-> new RuntimeException());
    }

    public String getValue() {
        return value;
    }
}
