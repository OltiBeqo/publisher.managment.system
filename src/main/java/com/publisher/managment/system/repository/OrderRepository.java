package com.publisher.managment.system.repository;

import com.publisher.managment.system.dto.projections.OrderSummary;
import com.publisher.managment.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    List<Order> findAllByLibraryId(Integer libraryId);
    List<Order> findByCourierId(Integer id);
    List<Order> findByDeleted(boolean isDeleted);
    @Query(value = "WITH counted_orders(library_id, total_orders) AS (\n" +
            "  SELECT library_id, COUNT(*) AS total_orders\n" +
            "  FROM orders\n" +
            "  WHERE order_status = 'COMPLETED' AND deleted = 0\n" +
            "    AND created_at >= DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH)\n" +
            "    AND created_at < DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "  GROUP BY library_id)" +
            "SELECT counted_orders.*\n" +
            "FROM counted_orders\n" +
            "WHERE counted_orders.total_orders = (\n" +
            "  SELECT MAX(counted_orders.total_orders)\n" +
            "  FROM counted_orders)", nativeQuery = true)
    OrderSummary findOrderSummary();
    @Query(value = "SELECT COUNT(id) AS total_orders\n" +
            "FROM orders\n" +
            "WHERE order_status = 'COMPLETED'\n" +
            "  AND created_at >= DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH)\n" +
            "  AND created_at < DATE_FORMAT(NOW(), '%Y-%m-01')", nativeQuery = true)
    OrderSummary.TotalOrders findOrdersCompleted();

}