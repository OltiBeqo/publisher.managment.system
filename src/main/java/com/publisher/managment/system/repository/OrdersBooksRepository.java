package com.publisher.managment.system.repository;


import com.publisher.managment.system.entity.OrdersBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersBooksRepository extends JpaRepository<OrdersBooks, Integer> {

    List<OrdersBooks> findByOrderId(Integer orderId);

}
