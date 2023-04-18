package com.publisher.managment.system.dto.auth;

import com.publisher.managment.system.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUtil {
    public static Integer getUserIdFromContext() {
        String userID = SecurityContextHolder.getContext().getAuthentication().getName().split(",")[0];
        return Integer.parseInt(userID);
    }

}
