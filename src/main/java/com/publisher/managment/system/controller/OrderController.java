package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.projections.OrderSummary;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Page<OrderDTO>> getOrdersPaginated(Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersPaginated(pageable));
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Page<OrderDTO>> searchOrder(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(orderService.searchOrder(request));
    }

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(isDeleted));
    }

    @TrackExecutionTime
    @GetMapping("/library/{libraryId}")
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
    @GetMapping("/total-orders")
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
    @PutMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDTO orderDTO) {
        orderService.updateOrderStatus(orderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/counted-orders")
    public ResponseEntity<OrderSummary> getCountedOrders(){
        return ResponseEntity.ok(orderService.countedOrders());
    }

    @GetMapping("/completed-orders")
    public ResponseEntity<OrderSummary.TotalOrders> getCompletedOrders(){
        return ResponseEntity.ok(orderService.findCompletedOrders());
    }
}
