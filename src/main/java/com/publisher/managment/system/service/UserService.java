package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.auth.AuthRequest;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.entity.enums.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDTO registerUser(AuthRequest request);

    List<UserDTO> getUsers();

    UserDTO getUserById(Integer id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUserById(Integer id);

    List<UserDTO> getUsersByRole(Role role);

    UserDTO getRandomCourier();

    Page<UserDTO> getUsersPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<UserDTO> searchUser(SearchRequest request);
}
