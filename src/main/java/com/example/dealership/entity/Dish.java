package com.example.dealership.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dish;
    private String category;
    private String description;
    private Integer weight;
    private Integer calories;
    private Boolean allergic;
    private Integer price;


    @ManyToMany(mappedBy = "dishList", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Order> orderList;
}