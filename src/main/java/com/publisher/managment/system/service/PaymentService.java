package com.publisher.managment.system.service;

import com.publisher.managment.system.entity.Payment;
@FunctionalInterface
public interface PaymentService {
    Payment addPayment(String method, Double amount);
}
