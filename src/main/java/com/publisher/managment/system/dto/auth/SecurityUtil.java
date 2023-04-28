package com.publisher.managment.system.dto.auth;

import org.springframework.security.core.context.SecurityContextHolder;
public class SecurityUtil {
    public static Integer getUserIdFromContext() {
        String userID = SecurityContextHolder.getContext().getAuthentication().getName().split(",")[0];
        return Integer.parseInt(userID);
    }

}
