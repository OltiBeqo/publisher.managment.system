package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;

import java.time.LocalDateTime;

public class PaymentMapper {
    public static PaymentDTO toDto(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .paymentMethod(payment.getPaymentMethod().getValue())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .order(payment.getOrder() != null ? OrderMapper.toDto(payment.getOrder()) : null )
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .deleted(payment.isDeleted())
                .build();
    }

    public static Payment toEntity(PaymentDTO paymentDTO) {
        return Payment.builder()
                .id(paymentDTO.getId())
                .paymentMethod(PaymentMethod.fromValue(paymentDTO.getPaymentMethod()))
                .amount(paymentDTO.getAmount())
                .order(paymentDTO.getOrder() != null ? OrderMapper.toEntity(paymentDTO.getOrder()) : null)
                .transactionId(paymentDTO.getTransactionId())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public static Payment toEntityForUpdate(Payment payment, PaymentDTO paymentDTO) {
        payment.setPaymentMethod(PaymentMethod.fromValue(paymentDTO.getPaymentMethod()));
        payment.setAmount(paymentDTO.getAmount());
        payment.setModifiedAt(LocalDateTime.now());
        return payment;
    }
}
