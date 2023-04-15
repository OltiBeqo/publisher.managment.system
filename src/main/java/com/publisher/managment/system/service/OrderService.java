package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.Book;
import com.publisher.managment.system.entity.enums.PaymentMethod;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO, Integer libraryId);

    List<OrderDTO> getOrders();

    OrderDTO getOrderById(Integer id);

    OrderDTO updateOrder(Integer id, OrderDTO orderDTO);

    void deleteOrderById(Integer id);

    void setOrderStatus(String status, Integer orderId);

    List<OrderDTO> getOrdersByClient(Integer libraryId);

//    List<OrderDTO> getOrdersByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> getOrdersAssigned(OrderDTO orderDTO, UserDTO userDTO);

    long getTotalOfOrders(Integer orderId);

    Double getTotalRevenue();

    Book setDiscount(Double percentage, Integer bookId);
}
