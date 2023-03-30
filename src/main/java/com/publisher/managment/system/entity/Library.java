package com.publisher.managment.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue
    private Integer id;
    private String library;
    private String address;
    private String email;
    private String phoneNumber;

}
