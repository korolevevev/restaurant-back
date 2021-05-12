package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * В данном классе объявляем основные параметры объекта Блюда,
 * с помощью декоратора Data автоматически создаём get/set методы для них
 */
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

    /**
     * Соединяем таблицу Dishes с Orders с помощью Bills на основе dishList (списка блюд)
     */
    @ManyToMany(mappedBy = "dishList", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Order> orderList;
}