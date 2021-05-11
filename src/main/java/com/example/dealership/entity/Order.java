package com.example.dealership.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "bills",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishList;
}