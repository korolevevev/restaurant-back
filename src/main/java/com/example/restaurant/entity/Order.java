package com.example.restaurant.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * В данном классе объявляем основные параметры объекта Заказа,
 * с помощью декоратора Data автоматически создаём get/set методы для них
 */
@Entity
@NoArgsConstructor
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Integer table_id;
    public String customer_name;
    public String customer_surname;
    public String waiter_name;
    public String waiter_surname;
    public Integer total;
    public Boolean paid;

    /**
     * Создаём промежуточную таблицу Bills, в которой соединяем Orders/Dishes по полям id
     */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "bills",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishList;
}