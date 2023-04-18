package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.enums.PaymentMethod;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(Integer id);

    OrderDTO updateOrder(Integer id, OrderDTO orderDTO);

    void deleteOrderById(Integer id);

    void updateOrderStatus(String status, Integer orderId);

    List<OrderDTO> getOrdersByClient(Integer libraryId);

    List<OrderDTO> getOrdersAssigned();
    List<OrderDTO> getOrdersByCourier(Integer courierId);

    long getTotalOfOrders();

    Double getTotalRevenue();
}
