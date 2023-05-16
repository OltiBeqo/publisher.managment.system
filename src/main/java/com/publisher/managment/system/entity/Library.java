package com.publisher.managment.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "libraries")
@SQLDelete(sql = "UPDATE libraries SET deleted = true WHERE id=?")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String library;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private boolean deleted = Boolean.FALSE;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
