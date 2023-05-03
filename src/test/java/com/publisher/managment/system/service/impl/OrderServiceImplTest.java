package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.repository.OrderRepository;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderRepository orderRepository;
    @MockBean
    private OrderService toTest;


    public void test_createOrder_ok() {

    }

    public void test_getOrders_ok() {

    }

    public void test_getOrderById_ok() {

    }

    public void test_getOrderById_ko() {

    }

    public void test_updateOrder_ok() {

    }

    public void test_deleteOrderById_ok() {

    }

    public void test_getOrdersAssigned_ok() {

    }

    public void test_getTotalOfOrders_ok() {

    }
}
