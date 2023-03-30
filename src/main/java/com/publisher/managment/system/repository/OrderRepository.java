package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderRepository {

    Order createOrder(Order order);
    List<Order> getOrders();
    Order getOrderById(Integer id);
    Order updateOrder(Integer id);
    void deleteOrderById(Integer id);
}
