package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);
    List<User> getUsers();
    User getUserById(Integer id);
    User updateUser(Integer id);
    void deleteUserById(Integer id);

}
