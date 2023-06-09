package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.projections.OrderSummary;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByStatus(boolean isDeleted);

    OrderDTO getOrderById(Integer id);

    void updateOrderStatus(OrderDTO orderDTO);

    void deleteOrderById(Integer id);

    List<OrderDTO> getOrdersByClient(Integer libraryId);

    List<OrderDTO> getOrdersAssigned();

    long getTotalOfOrders();

    Page<OrderDTO> getOrdersPaginated(Pageable pageable);

    Page<OrderDTO> searchOrder(SearchRequest request);

    OrderSummary countedOrders();

    OrderSummary.TotalOrders findCompletedOrders();

}
