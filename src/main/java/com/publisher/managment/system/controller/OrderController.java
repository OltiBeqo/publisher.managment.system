package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(isDeleted));
    }

    @TrackExecutionTime
    @GetMapping("library/{libraryId}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable Integer libraryId) {
        return ResponseEntity.ok(orderService.getOrdersByClient(libraryId));
    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @TrackExecutionTime
    @GetMapping("/totalOrders")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Long> getTotalOfOrders() {
        return ResponseEntity.ok(orderService.getTotalOfOrders());
    }

    @TrackExecutionTime
    @GetMapping("/assigned")
    @RolesAllowed({"COURIER"})
    public ResponseEntity<List<OrderDTO>> getOrdersAssigned() {
        return ResponseEntity.ok(orderService.getOrdersAssigned());
    }

    @TrackExecutionTime
    @PostMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @TrackExecutionTime
    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
