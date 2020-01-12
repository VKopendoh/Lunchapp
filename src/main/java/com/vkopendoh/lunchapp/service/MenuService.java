package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.model.Dish;
import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.repository.DishRepository;
import com.vkopendoh.lunchapp.repository.MenuRepository;
import com.vkopendoh.lunchapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;

    @Autowired
    private DishRepository dishRepository;

    public Menu get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Menu not found, id: " + id));
    }

    public Menu create(Menu menu) {
        menu.setDishes(setMenuOnDishes(menu));
        return repository.save(menu);
    }

    public Menu update(Menu menu, int menuId) {
        Menu updated = get(menuId);
        updated.setCreateDate(LocalDate.now());
        updated.setDishes(menu.getDishes());
        dishRepository.delete(menuId);
        setMenuOnDishes(updated);
        return repository.save(updated);
    }

    private List<Dish> setMenuOnDishes(Menu menu) {
        List<Dish> dishes = new ArrayList<>();
        for (Dish dish : menu.getDishes()) {
            dish.setMenu(menu);
            dishes.add(dish);
        }
        return dishes;
    }
}
