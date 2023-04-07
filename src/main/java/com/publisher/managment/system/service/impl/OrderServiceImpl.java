package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ExceptionMessage implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntity(orderDTO)));
    }

    @Override
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        return OrderMapper.toDto(orderRepository.save(OrderMapper.toEntityForUpdate(order, orderDTO)));
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, id)));
        orderRepository.delete(order);
    }

    @Override
    public void setOrderStatus(String status) {

    }

    @Override
    public List<OrderDTO> getOrdersByClient(Integer libraryId) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByPaymentMethod(String paymentMethod) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersAssigned(OrderDTO orderDTO, UserDTO userDTO) {
        return null;
    }

    @Override
    public Integer getTotalOfOrders(Integer orderId) {
        return null;
    }

    @Override
    public Double getTotalRevenue(Double orderAmount) {
       return null;
    }
}
