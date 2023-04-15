package com.publisher.managment.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Integer id;
    private String paymentMethod;
    private double amount;
    private String transactionId;
    private LocalDate createdAt;
}
