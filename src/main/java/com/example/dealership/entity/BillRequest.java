package com.example.dealership.entity;

import lombok.Data;
@Data
public class BillRequest {
    private Order order;
    private Long[] dishList;
}
