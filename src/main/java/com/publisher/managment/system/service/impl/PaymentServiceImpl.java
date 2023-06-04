package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.dto.projections.PaymentSummary;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.search.SearchSpecification;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.OrderMapper;
import com.publisher.managment.system.mapper.PaymentMapper;
import com.publisher.managment.system.repository.PaymentRepository;
import com.publisher.managment.system.service.OrderService;
import com.publisher.managment.system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl extends ExceptionMessage implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Override
    public Page<PaymentDTO> getPaymentsPaginated(Pageable pageable) {

        Page<Payment> paymentPage = paymentRepository.findAll(pageable);
        List<Payment> paymentList = paymentPage.getContent();
        List<PaymentDTO> content = paymentList.stream().map(PaymentMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, paymentPage.getPageable(), paymentPage.getSize());
    }

    @Override
    public Page<PaymentDTO> searchPayment(SearchRequest request) {
        SearchSpecification<Payment> specification = new SearchSpecification<>(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Payment> paymentPage = paymentRepository.findAll(specification, pageable);
        List<Payment> paymentList = paymentPage.getContent();
        List<PaymentDTO> content = paymentList.stream().map(PaymentMapper::toDto).collect(Collectors.toList());

        return new PageImpl<>(content, paymentPage.getPageable(), paymentPage.getSize());
    }

    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        OrderDTO orderDTO = orderService.getOrderById(paymentDTO.getOrder().getId());
        Order order = OrderMapper.toEntity(orderDTO);
        Optional<Payment> payment = paymentRepository.findByOrderId(order.getId());
        if (payment.isPresent()) {
            throw new BadRequestException(String.format(PAYMENT_EXISTS, order.getId()));
        } else if (!order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
            throw new BadRequestException(String.format(ORDER_NOT_COMPLETED, order.getId(), order.getOrderStatus()));
        }
        paymentDTO.setOrder(orderDTO);
        paymentDTO.setAmount(orderDTO.getTotalAmount());
        return PaymentMapper.toDto(paymentRepository.save(PaymentMapper.toEntity(paymentDTO)));
    }

    @Override
    public PaymentDTO updatePaymentMethod(PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(paymentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PAYMENT_NOT_FOUND, paymentDTO.getId())));
        payment.setPaymentMethod(PaymentMethod.fromValue(paymentDTO.getPaymentMethod()));
        return PaymentMapper.toDto(paymentRepository.save(PaymentMapper.updatePaymentMethod(payment, paymentDTO)));
    }

    @Override
    public Double getTotalRevenue() {
        return paymentRepository.findAll().stream().map(Payment::getAmount).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(boolean isDeleted) {
        return paymentRepository.findByDeleted(isDeleted).stream().map(PaymentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(PaymentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PAYMENT_NOT_FOUND, paymentId)));
    }

    @Override
    public void deletePayment(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PAYMENT_NOT_FOUND, paymentId)));
        paymentRepository.delete(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod) {
        return paymentRepository.findAllByPaymentMethod(paymentMethod).stream().map(PaymentMapper::toDto).collect(Collectors.toList());
    }

    public PaymentSummary getPaymentSummary() {
        return paymentRepository.getPaymentSummary();
    }
}