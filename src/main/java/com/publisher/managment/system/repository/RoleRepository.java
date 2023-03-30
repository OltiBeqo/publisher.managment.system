package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Role;

import java.util.List;

public interface RoleRepository {
    Role addRole(Role role);
    List<Role> getRoles();
    Role getRoleById(Integer id);
    Role updateRole(Integer id);
    void deleteRoleById(Integer id);
}
