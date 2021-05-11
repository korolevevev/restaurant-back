package com.example.dealership.service;

import com.example.dealership.entity.BillRequest;
import com.example.dealership.entity.Order;
import com.example.dealership.entity.Dish;
import com.example.dealership.repository.DishRepository;
import com.example.dealership.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DishRepository dishRepository;

    public void create(BillRequest billRequest){
        Order order = orderRepository.save(billRequest.getOrder());
        List<Long> dishesIds = Arrays.asList(billRequest.getDishList());
        List<Dish> dishList = new ArrayList<>();
        dishesIds.forEach(id -> {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dishList.add(dish);
        });
        order.setDishList(dishList);
        orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void update(Order order, Long id){
        var updatedOrder = orderRepository.findById(id);

        if (updatedOrder.isPresent())
        {
            var _updatedOrder = updatedOrder.get();
            _updatedOrder.setTable_id(order.getTable_id() != null ? order.getTable_id() : _updatedOrder.getTable_id());
            _updatedOrder.setCustomer_name(order.getCustomer_name() != null ? order.getCustomer_name() : _updatedOrder.getCustomer_name());
            _updatedOrder.setCustomer_surname(order.getCustomer_surname() != null ? order.getCustomer_surname() : _updatedOrder.getCustomer_surname());
            _updatedOrder.setWaiter_name(order.getWaiter_name() != null ? order.getWaiter_name() : _updatedOrder.getWaiter_name());
            _updatedOrder.setWaiter_surname(order.getWaiter_surname() != null ? order.getWaiter_surname() : _updatedOrder.getWaiter_surname());
            _updatedOrder.setTotal(order.getTotal() != null ? order.getTotal() : _updatedOrder.getTotal());
            _updatedOrder.setPaid(order.getPaid() != null ? order.getPaid() : _updatedOrder.getPaid());
            orderRepository.save(_updatedOrder);
        }


    }

    public String DeleteById(Long id) {
        orderRepository.deleteById(id);
        return  "Заказ удалён";
    }
}
