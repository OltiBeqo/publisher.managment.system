package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class OrderRepositoryImpl extends NamedQueries implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Order createOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public List<Order> getOrders() {
        return entityManager.createNamedQuery(GET_ORDERS, Order.class).getResultList();
    }

    @Override
    public Order getOrderById(Integer id) {
        return entityManager.createNamedQuery(GET_ORDERS_BY_ID, Order.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Order updateOrder(Integer id) {
        Order order = getOrderById(id);
        return entityManager.merge(order);
    }

    @Override
    public void deleteOrderById(Integer id) {
        Order order = getOrderById(id);
        entityManager.remove(order);
    }
}
