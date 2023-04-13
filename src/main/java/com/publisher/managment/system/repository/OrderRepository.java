package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<OrderDTO> findAllOrderByLibrary(Integer libraryId);

}
