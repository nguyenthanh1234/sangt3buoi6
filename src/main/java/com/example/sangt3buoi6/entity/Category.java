package com.example.sangt3buoi6.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {
    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Name must not be empty")
    @Size(max = 50,min = 1, message = "Name must be less than 50 characters")
    @Column(name = "tenphong")
    private String tenCategory;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> categories;
}
