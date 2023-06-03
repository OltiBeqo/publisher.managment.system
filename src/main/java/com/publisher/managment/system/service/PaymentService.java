package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {
    PaymentDTO addPayment(PaymentDTO paymentDTO);

    PaymentDTO updatePaymentMethod(PaymentDTO paymentDTO);

    List<PaymentDTO> getPaymentsByStatus(boolean isDeleted);

    PaymentDTO getPaymentById(Integer paymentId);

    Double getTotalRevenue();

    void deletePayment(Integer paymentId);

    List<PaymentDTO> getPaymentsByMethod(PaymentMethod paymentMethod);

    Page<PaymentDTO> getPaymentsPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<PaymentDTO> searchPayment(SearchRequest request);
}
