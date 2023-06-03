package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;

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

    Page<OrderDTO> getOrdersPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<OrderDTO> searchOrder(SearchRequest request);

}
