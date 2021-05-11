package com.example.restaurant.entity;

import lombok.Data;
@Data
public class BillRequest {
    private Order order;
    private Long[] dishList;
}
