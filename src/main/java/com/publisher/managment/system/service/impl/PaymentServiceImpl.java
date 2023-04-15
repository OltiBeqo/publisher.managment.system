package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment addPayment(String paymentMethod, Double amount) {
        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.fromValue(paymentMethod));
        payment.setAmount(amount);
        payment.setTransactionId(UUID.randomUUID().toString());
        return payment;
    }
}
