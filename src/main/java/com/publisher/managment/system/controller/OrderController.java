package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.request.AppConstants;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.response.PageResponse;
import com.publisher.managment.system.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<PageResponse<OrderDTO>> getOrdersPaginated(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PageResponse<OrderDTO> response = new PageResponse<>();
        response.setPageStats(orderService.getOrdersPaginated(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<PageResponse<OrderDTO>> searchOrder(@RequestBody SearchRequest request){
        PageResponse<OrderDTO> response = new PageResponse<>();
        response.setPageStats(orderService.searchOrder(request));
        return ResponseEntity.ok(response);
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
}
