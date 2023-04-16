package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.entity.enums.PaymentMethod;

import java.util.List;

public interface PaymentService {
    PaymentDTO addPayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getPayments();
    PaymentDTO getPaymentById(Integer paymentId);
    void deletePayment(Integer paymentId);
    List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod);

}
