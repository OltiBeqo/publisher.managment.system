package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>, JpaSpecificationExecutor<Payment> {
    List<Payment> findAllByPaymentMethod(PaymentMethod paymentMethod);
    List<Payment> findByDeleted(boolean isDeleted);
    Optional <Payment> findByOrderId(Integer orderId);
}
