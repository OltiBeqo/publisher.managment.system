package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.User;
import com.publisher.managment.system.repository.UserRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserRepositoryImpl extends NamedQueries implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createNamedQuery(GET_USERS, User.class).getResultList();
    }

    @Override
    public User getUserById(Integer id) {
        return entityManager.createNamedQuery(GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public User updateUser(Integer id) {
        User user = getUserById(id);
        return entityManager.merge(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = getUserById(id);
        entityManager.remove(id);
    }
}
