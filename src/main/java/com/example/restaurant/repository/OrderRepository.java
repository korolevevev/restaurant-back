package com.example.restaurant.repository;

import com.example.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * интерфейс для доступа к данным заказов в БД через ORM
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}