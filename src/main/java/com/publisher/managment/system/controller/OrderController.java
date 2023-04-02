package com.publisher.managment.system.controller;

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
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
