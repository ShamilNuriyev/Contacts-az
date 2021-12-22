package com.example.backend.entity;

import com.example.backend.dao.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;


    private String username;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String city;

    private String phoneNumber;

    private String price;


    @Column(length = 1000000)
    private String pictureUrl;

    private String aboutMyself;


}
