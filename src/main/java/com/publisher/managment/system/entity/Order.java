package com.publisher.managment.system.entity;

import com.publisher.managment.system.entity.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double totalAmount;
    private double discount;
    private String comment;
    @CreatedDate
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<Book> book = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "payment_id",referencedColumnName = "id")
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private User courier;

}
