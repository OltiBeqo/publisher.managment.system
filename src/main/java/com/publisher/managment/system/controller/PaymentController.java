package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.PaymentDTO;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(isDeleted));
    }

    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @GetMapping("/method/{paymentMethod}")
    public ResponseEntity<List<PaymentDTO>> getPaymentByMethod(@PathVariable PaymentMethod paymentMethod) {
        return ResponseEntity.ok(paymentService.getPaymentsByMethod(paymentMethod));
    }

    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    @PostMapping
    public ResponseEntity<PaymentDTO> addPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.addPayment(paymentDTO));
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
