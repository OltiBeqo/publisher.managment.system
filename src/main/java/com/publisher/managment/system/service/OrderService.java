package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByStatus(boolean isDeleted);

    OrderDTO getOrderById(Integer id);

    void updateOrderStatus(OrderDTO orderDTO);

    void deleteOrderById(Integer id);

    List<OrderDTO> getOrdersByClient(Integer libraryId);

    List<OrderDTO> getOrdersAssigned();

    List<OrderDTO> getOrdersByCourier(Integer courierId);

    long getTotalOfOrders();


}
