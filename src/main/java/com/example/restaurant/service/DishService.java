package com.example.restaurant.service;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для блюд, который через репозиторий обращается к ORM
 * Нужен для скрытия данных от RESTapi (чтобы REST обращался именно к сервису с помощью методов CRUD)
 */
@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> create(Dish dish){
        dishRepository.save(dish);
        return dishRepository.findAll();
    }

    /**
     * Метод для изменения данных о блюде
     * @param dish передаём экземпляр объекта Блюда
     * @return обращаемся к репозиторию через ORM и запрашиваем информацию о всех блюдах
     */
    public List<Dish> update(Dish dish){
        var updatedDish = dishRepository.findById(dish.getId());

        if (updatedDish.isPresent())
        {
            var _updatedDish = updatedDish.get();
            _updatedDish.setDish(dish.getDish() != null ? dish.getDish() : _updatedDish.getDish());
            _updatedDish.setCategory(dish.getCategory() != null ? dish.getCategory() : _updatedDish.getCategory());
            _updatedDish.setDescription(dish.getDescription() != null ? dish.getDescription() : _updatedDish.getDescription());
            _updatedDish.setWeight(dish.getWeight() != null ? dish.getWeight() : _updatedDish.getWeight());
            _updatedDish.setCalories(dish.getCalories() != null ? dish.getCalories() : _updatedDish.getCalories());
            _updatedDish.setAllergic(dish.getAllergic() != null ? dish.getAllergic() : _updatedDish.getAllergic());
            _updatedDish.setPrice(dish.getPrice() != null ? dish.getPrice() : _updatedDish.getPrice());
            dishRepository.save(_updatedDish);
        }

        return dishRepository.findAll();
    }

    /**
     * Поиск всех блюд
     * @return обраащется к репозиторию через ORM и вытаскивает все блюда
     */
    public List<Dish> findAll(){
        return dishRepository.findAll();
    }

    /**
     * Поиск конкретного блюда
     * @return обраащется к репозиторию через ORM и вытаскивает блюдо по id
     */
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    /**
     * Удаление конкретного блюда
     * @return обраащется к репозиторию через ORM и удаляет блюдо по id
     */
    public String DeleteById(Long id) {
        dishRepository.deleteById(id);
        return  "Блюдо удалено";
    }
}
