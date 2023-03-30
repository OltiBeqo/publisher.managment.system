package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrders() {
        return null;
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(Integer id) {
        return null;
    }

    @Override
    public void deleteOrderById(Integer id) {

    }
}
