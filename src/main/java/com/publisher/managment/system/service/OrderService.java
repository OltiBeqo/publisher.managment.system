package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    List<OrderDTO> getOrders();
    OrderDTO getOrderById(Integer id);
    OrderDTO updateOrder(Integer id, OrderDTO orderDTO);
    void deleteOrderById(Integer id);
}
