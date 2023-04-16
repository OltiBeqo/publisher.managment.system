package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByPaymentMethod(PaymentMethod paymentMethod);
}
