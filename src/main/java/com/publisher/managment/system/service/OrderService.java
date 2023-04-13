package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.Book;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO, Integer libraryId);

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(Integer id);

    OrderDTO updateOrder(Integer id, OrderDTO orderDTO);

    void deleteOrderById(Integer id);

    void setOrderStatus(String status, Integer orderId);

    List<OrderDTO> getOrdersByClient(Integer libraryId);

    List<OrderDTO> getOrdersByPaymentMethod(String paymentMethod);

    List<OrderDTO> getOrdersAssigned(OrderDTO orderDTO, UserDTO userDTO);

    Integer getTotalOfOrders(Integer orderId);

    Double getTotalRevenue(Double orderAmount);

    Book setDiscount(Double percentage, BookDTO bookDTO);
}
