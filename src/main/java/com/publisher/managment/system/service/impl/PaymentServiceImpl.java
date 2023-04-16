package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.PaymentMapper;
import com.publisher.managment.system.repository.PaymentRepository;
import com.publisher.managment.system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl extends ExceptionMessage implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO){
        return PaymentMapper.toDto(paymentRepository.save(PaymentMapper.toEntity(paymentDTO)));
    }

    @Override
    public List<PaymentDTO> getPayments() {
        return paymentRepository.findAll().stream().map(PaymentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId).map(PaymentMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(String.format(PAYMENT_NOT_FOUND, paymentId)));
    }

    @Override
    public void deletePayment(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException(String.format(PAYMENT_NOT_FOUND, paymentId)));
        paymentRepository.delete(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod) {
        return paymentRepository.findAllByPaymentMethod(paymentMethod).stream().map(PaymentMapper::toDto).collect(Collectors.toList());
    }
}
