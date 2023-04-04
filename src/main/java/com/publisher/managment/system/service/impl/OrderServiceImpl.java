package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDTO)));
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(order -> OrderMapper.toDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id).map(order -> OrderMapper.toDto(order)).orElseThrow(()-> new RuntimeException());
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntityForUpdate(order, orderDTO)));
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException());
        orderRepository.delete(order);
    }
}
