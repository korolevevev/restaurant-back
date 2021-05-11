package com.example.dealership.controller;

import com.example.dealership.entity.BillRequest;
import com.example.dealership.entity.Dish;
import com.example.dealership.entity.Order;
import com.example.dealership.repository.DishRepository;
import com.example.dealership.repository.OrderRepository;
import com.example.dealership.service.DishService;
import com.example.dealership.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class RESTController {
    private final DishService dishService;
    private final OrderService orderService;

    @Autowired
    public RESTController(DishService dishService, OrderService orderService) {
        this.dishService = dishService;
        this.orderService = orderService;
    }

    //dish controller
    @GetMapping(value = "/api/dishes")
    public ResponseEntity<List<Dish>> findAllDishes(){
        final List<Dish> dishesList = dishService.findAll();

        return dishesList != null && !dishesList.isEmpty()
                ? new ResponseEntity<>(dishesList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/dishes/{id}")
    public ResponseEntity<Optional<Dish>> findById(@PathVariable(name = "id") Long id) {
        final Optional<Dish> dishes= dishService.findById(id);

        return dishes.isPresent()
                ? new ResponseEntity<>(dishes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/api/dishes")
    public List<Dish> createDish(@RequestBody Dish dish){
        dishService.create(dish);
        return dishService.findAll();
    }

    @PutMapping("/api/dishes/update")
    public ResponseEntity<List<Dish>> updateCar(@RequestBody Dish dish) {
        final List<Dish> dishes = dishService.update(dish);

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/dishes/{id}")
    public ResponseEntity<String> DeleteDishById(@PathVariable(name = "id") Long id) {
        String message = dishService.DeleteById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // order controller
    @GetMapping(value = "/api/orders")
    public ResponseEntity<List<Order>> findAllOrders(){
        final List<Order> ordersList = orderService.findAll();

        return ordersList != null && !ordersList.isEmpty()
                ? new ResponseEntity<>(ordersList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "id") Long id) {
        final Order order = orderService.findById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PostMapping(value = "/api/orders")
    public List<Order> addOrder(@RequestBody BillRequest billRequest){
        orderService.create(billRequest);
        return orderService.findAll();
    }

    @PutMapping("/api/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderService.update(order, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
