package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import java.time.LocalDateTime;

public class PaymentMapper {
    public static PaymentDTO toDto(Payment payment){
        return PaymentDTO.builder()
                .id(payment.getId())
                .paymentMethod(payment.getPaymentMethod().getValue())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Payment toEntity(PaymentDTO paymentDTO){
        return Payment.builder()
                .id(paymentDTO.getId())
                .paymentMethod(PaymentMethod.fromValue(paymentDTO.getPaymentMethod()))
                .amount(paymentDTO.getAmount())
                .transactionId(paymentDTO.getTransactionId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
