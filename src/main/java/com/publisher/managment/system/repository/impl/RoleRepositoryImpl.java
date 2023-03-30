package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.Role;
import com.publisher.managment.system.repository.RoleRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleRepositoryImpl extends NamedQueries implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Role addRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createNamedQuery(GET_ROLES, Role.class).getResultList();
    }

    @Override
    public Role getRoleById(Integer id) {
        return entityManager.createNamedQuery(GET_ROLES_BY_ID, Role.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Role updateRole(Integer id) {
        Role role = getRoleById(id);
        return entityManager.merge(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        Role role = getRoleById(id);
        entityManager.remove(role);
    }
}
