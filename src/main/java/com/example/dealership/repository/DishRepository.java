package com.example.dealership.repository;

import com.example.dealership.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Long> {
}