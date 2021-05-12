package com.example.restaurant.entity;

import lombok.Data;

//С помощью данного класса соединяем основные таблицы Orders/Dishes
@Data
public class BillRequest {
    private Order order;
    private Long[] dishList;
}
