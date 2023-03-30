package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.RoleDTO;
import com.publisher.managment.system.mapper.RoleMapper;
import com.publisher.managment.system.repository.RoleRepository;
import com.publisher.managment.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        return RoleMapper.toDto(roleRepository.addRole(RoleMapper.toEntity(roleDTO)));
    }

    @Override
    public List<RoleDTO> getRoles() {
        return roleRepository.getRoles().stream().map(role -> RoleMapper.toDto(role)).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Integer id) {
        return RoleMapper.toDto(roleRepository.getRoleById(id));
    }

    @Override
    public RoleDTO updateRole(Integer id) {
        return null;
    }

    @Override
    public void deleteRoleById(Integer id) {

    }
}
