package com.example.restaurant.controller;

import com.example.restaurant.entity.BillRequest;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Order;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RESTController {
    private final DishService dishService;
    private final OrderService orderService;

    /**
     * Контроллер принимает сервисы
     * @param dishService сервис для блюд
     * @param orderService сервис для заказов
     */
    @Autowired
    public RESTController(DishService dishService, OrderService orderService) {
        this.dishService = dishService;
        this.orderService = orderService;
    }

    /**
     * Метод для получения списка блюд
     * @return возвращает список блюд (dishesList) или ошибку (не найдено)
     */
    @GetMapping(value = "/api/dishes")
    public ResponseEntity<List<Dish>> findAllDishes(){
        final List<Dish> dishesList = dishService.findAll();

        return dishesList != null && !dishesList.isEmpty()
                ? new ResponseEntity<>(dishesList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод для получения данных о блюде по id
     * @param id - id блюда
     * @return возвращает информацию о блюде по указанному id или ошибку (блюдо с данным id не найдено)
     */
    @GetMapping("/api/dishes/{id}")
    public ResponseEntity<Optional<Dish>> findById(@PathVariable(name = "id") Long id) {
        final Optional<Dish> dishes= dishService.findById(id);

        return dishes.isPresent()
                ? new ResponseEntity<>(dishes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод для создания нового блюда в меню
     * @param dish передаём блюдо
     * @return возвращаем изменённый список блюд
     */
    @PostMapping(value = "/api/dishes")
    public List<Dish> createDish(@RequestBody Dish dish){
        dishService.create(dish);
        return dishService.findAll();
    }

    /**
     * Метод для изменения данных о блюде
     * @param dish передаём изменённое блюдо
     * @return возвращаем изменённый список блюд
     */
    @PutMapping("/api/dishes/update")
    public ResponseEntity<List<Dish>> updateDish (@RequestBody Dish dish) {
        final List<Dish> dishes = dishService.update(dish);

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    /**
     * Метод для изменения удаления блюда по id
     * @param id - передаём id блюда
     * @return возвращаем сообщение об удалении блюда
     */
    @DeleteMapping(value = "/api/dishes/{id}")
    public ResponseEntity<String> DeleteDishById(@PathVariable(name = "id") Long id) {
        String message = dishService.DeleteById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Метод для получения списка заказов
     * @return возвращает список заказов (ordersList) или ошибку (не найдено)
     */
    @GetMapping(value = "/api/orders")
    public ResponseEntity<List<Order>> findAllOrders(){
        final List<Order> ordersList = orderService.findAll();

        return ordersList != null && !ordersList.isEmpty()
                ? new ResponseEntity<>(ordersList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Метод для получения данных о заказе по id
     * @param id - id заказа
     * @return возвращает данные о блюде с указанным id
     */
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "id") Long id) {
        final Order order = orderService.findById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Создание нового заказа и автоматическое создание чека вместе с ним
     * @param billRequest передаём order и dishList (соединяем таблицы Orders и Dishes)
     * @return - возвращаем список заказов
     */
    @PostMapping(value = "/api/orders")
    public List<Order> addOrder(@RequestBody BillRequest billRequest){
        orderService.create(billRequest);
        return orderService.findAll();
    }

    /**
     * изменение заказа по id
     * @param id передаём id заказа
     * @param order передаём экземпляр заказа с указанным id
     * @return возвращаем статус 200 ok без сообщения
     */
    @PutMapping("/api/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderService.update(order, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
