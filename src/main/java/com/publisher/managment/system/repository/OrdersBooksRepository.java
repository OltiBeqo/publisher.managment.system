package com.publisher.managment.system.repository;


import com.publisher.managment.system.dto.projections.BookSummary;
import com.publisher.managment.system.entity.OrdersBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersBooksRepository extends JpaRepository<OrdersBooks, Integer> {

    List<OrdersBooks> findByOrderId(Integer orderId);

    @Query(value = "WITH counted_books AS (\n" +
            "  SELECT book_id\n" +
            "  FROM orders_books\n" +
            "  WHERE created_at >= DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL 1 MONTH)\n" +
            "    AND created_at < DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "  GROUP BY book_id\n" +
            "  ORDER BY SUM(book_quantity) DESC\n" +
            "  LIMIT 1)\n" +
            "SELECT *\n" +
            "FROM counted_books", nativeQuery = true)
    BookSummary findBookSummary();

}
