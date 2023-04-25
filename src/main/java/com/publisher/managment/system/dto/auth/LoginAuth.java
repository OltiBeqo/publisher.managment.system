package com.publisher.managment.system.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
public class LoginAuth {

    @NotNull
    private String username;
    @NotNull
    private String password;

}
