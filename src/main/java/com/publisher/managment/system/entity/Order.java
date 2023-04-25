package com.publisher.managment.system.entity;

import com.publisher.managment.system.entity.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(nullable = false)
    private Double totalAmount;
    private double discount = 1.0;
    private String comment;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean deleted = Boolean.FALSE;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private User courier;

}
