package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.dto.request.AppConstants;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.response.PageResponse;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<PageResponse<PaymentDTO>> getPaymentsPaginated(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PageResponse<PaymentDTO> response = new PageResponse<>();
        response.setPageStats(paymentService.getPaymentsPaginated(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<PageResponse<PaymentDTO>> searchPayment(@RequestBody SearchRequest request) {
        PageResponse<PaymentDTO> response = new PageResponse<>();
        response.setPageStats(paymentService.searchPayment(request));
        return ResponseEntity.ok(response);
    }


    @TrackExecutionTime
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(isDeleted));
    }

    @TrackExecutionTime
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @TrackExecutionTime
    @GetMapping("/total-revenue")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Double> getTotalRevenue() {
        return ResponseEntity.ok(paymentService.getTotalRevenue());
    }

    @TrackExecutionTime
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping("/method/{paymentMethod}")
    public ResponseEntity<List<PaymentDTO>> getPaymentByMethod(@PathVariable PaymentMethod paymentMethod) {
        return ResponseEntity.ok(paymentService.getPaymentsByMethod(paymentMethod));
    }

    @TrackExecutionTime
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @PutMapping
    public ResponseEntity<PaymentDTO> updatePaymentMethod(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.updatePaymentMethod(paymentDTO));
    }

    @TrackExecutionTime
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @PostMapping
    public ResponseEntity<PaymentDTO> addPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.addPayment(paymentDTO));
    }

    @TrackExecutionTime
    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
