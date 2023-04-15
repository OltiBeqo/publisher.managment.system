package com.publisher.managment.system.controller;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import com.publisher.managment.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired private OrderService orderService;
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }
    @GetMapping("/orders/library/{libraryId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable Integer libraryId){
        return ResponseEntity.ok(orderService.getOrdersByClient(libraryId));
    }
//    @GetMapping("/orders/payment/{paymentMethod}")
//    public ResponseEntity<List<OrderDTO>> getOrdersByPaymentMethod(@PathVariable PaymentMethod paymentMethod){
//        return ResponseEntity.ok(orderService.getOrdersByPaymentMethod(paymentMethod));
//    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @PostMapping("/newOrder/{libraryId}")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO, @PathVariable Integer libraryId){
        return ResponseEntity.ok(orderService.createOrder(orderDTO, libraryId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
