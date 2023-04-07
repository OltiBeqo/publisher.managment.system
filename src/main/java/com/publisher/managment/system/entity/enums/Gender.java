package com.publisher.managment.system.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String value;
    public static Gender fromValue(String value){
        return Arrays.asList(Gender.values()).stream()
                .filter(g -> g.value.equals(value))
                .findFirst().orElseThrow(()-> new RuntimeException(String.format("Gender %s doesn't exists", value)));
    }

    public String getValue() {
        return value;
    }
}
