package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByLibraryId(Integer libraryId);
    List<Order> findByCourierId(Integer id);
    List<Order> findByDeleted(boolean isDeleted);

}
