package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Integer id;
    private String paymentMethod;
    private double amount;
    private String transactionId;
    private LocalDateTime createdAt;
    private Integer orderId;
    private LocalDateTime modifiedAt;
}
