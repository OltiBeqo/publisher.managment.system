package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.RoleDTO;
import com.publisher.managment.system.entity.Role;

public class RoleMapper {

    public static RoleDTO toDto(Role role){
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDTO roleDTO){
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .build();
    }

    public static Role toEntityForUpdate(Role role, RoleDTO roleDTO){
        role.setName(roleDTO.getName());
        return role;
    }
}
