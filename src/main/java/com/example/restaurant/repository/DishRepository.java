package com.example.restaurant.repository;

import com.example.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * интерфейс для доступа к данным блюд в БД через ORM
 */
public interface DishRepository extends JpaRepository<Dish,Long> {
}