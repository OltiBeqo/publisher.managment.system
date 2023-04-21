package com.publisher.managment.system.dto;

import com.publisher.managment.system.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Integer id;
    private String paymentMethod;
    private double amount;
    private String transactionId = UUID.randomUUID().toString();
    private LocalDateTime createdAt;
    private OrderDTO order;
    private LocalDateTime modifiedAt;
    private boolean deleted;
}
