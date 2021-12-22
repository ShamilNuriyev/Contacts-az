package com.example.backend.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id1;

        @NotNull(message = "Name is required.")
        @NotEmpty
        @Basic(optional = false)
        private String firstName;

        @NotNull(message = "lastName is required.")
        @NotEmpty
        @Basic(optional = false)
        private String lastName;

        @NotNull(message = "Username is required.")
        @NotEmpty
        @Basic(optional = false)
        @Size(max = 50)
        @Email
        private String username;

        @NotNull(message = "Category name is required.")
        @Enumerated(EnumType.STRING)
        private Category category;

        @NotNull(message = "City is required.")
        @NotEmpty
        @Basic(optional = false)
        private String city;

        @NotNull(message = "Phone number  is required.")
        @NotEmpty
        private String phoneNumber;

        @NotNull(message = "Price  is required.")
        @NotEmpty
        private String price;

        @NotNull(message = "Picture  is required.")
        @NotEmpty
        private String pictureUrl;

        @NotNull(message = "About  is required.")
        @NotEmpty
        private String aboutMyself;

}


