package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.OrderDTO;
import com.publisher.managment.system.dto.search.SearchSpecification;
import com.publisher.managment.system.entity.Order;
import com.publisher.managment.system.entity.enums.OrderStatus;
import com.publisher.managment.system.entity.enums.PaymentMethod;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    List<Order> findAllByLibraryId(Integer libraryId);
    List<Order> findByCourierId(Integer id);
    List<Order> findByDeleted(boolean isDeleted);

}