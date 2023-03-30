package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO addRole(RoleDTO roleDTO);
    List<RoleDTO> getRoles();
    RoleDTO getRoleById(Integer id);
    RoleDTO updateRole(Integer id);
    void deleteRoleById(Integer id);
}
