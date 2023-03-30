package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.User;
import org.mapstruct.Mapper;

import java.time.LocalDate;
@Mapper
public class UserMapper {
    public static UserDTO toDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .createdAt(LocalDate.now())
                .build();
    }
    public static User toEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .username(userDTO.getUsername())
                .createdAt(LocalDate.now())
                .build();
    }
    public static User toEntityForUpdate(User user, UserDTO userDTO){
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        return user;
    }

}
