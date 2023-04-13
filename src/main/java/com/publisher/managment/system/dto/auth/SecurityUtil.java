package com.publisher.managment.system.dto.auth;

import com.publisher.managment.system.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication();
    }
}
