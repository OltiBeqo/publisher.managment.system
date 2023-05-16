package com.publisher.managment.system.entity;

import com.publisher.managment.system.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "payments")
@SQLDelete(sql = "UPDATE payments SET deleted = true WHERE id=?")
public class Payment {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false, unique = true)
    private String transactionId;
    private boolean deleted = Boolean.FALSE;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
