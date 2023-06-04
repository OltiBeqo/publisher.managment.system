package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.projections.PaymentSummary;
import com.publisher.managment.system.entity.Payment;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>, JpaSpecificationExecutor<Payment> {
    List<Payment> findAllByPaymentMethod(PaymentMethod paymentMethod);
    List<Payment> findByDeleted(boolean isDeleted);
    Optional <Payment> findByOrderId(Integer orderId);
    @Query(value = "WITH counted_payments AS (\n" +
            "  SELECT SUM(amount) AS total_amount, COUNT(*) AS total_payments\n" +
            "  FROM PAYMENTS\n" +
            "  WHERE created_at >= DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH)\n" +
            "    AND created_at < DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "    AND deleted = 0)\n" +
            "SELECT total_amount, total_payments\n" +
            "FROM counted_payments", nativeQuery = true)
    PaymentSummary getPaymentSummary();
}
