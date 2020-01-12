package com.vkopendoh.lunchapp.util;

import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.to.DishTo;
import com.vkopendoh.lunchapp.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuUtil {
    public static MenuTo getTo(Menu menu) {
        List<DishTo> dishes = menu.getDishes() == null ? null : menu.getDishes()
                .stream()
                .map(dish -> new DishTo(dish.getName(), dish.getPrice()))
                .collect(Collectors.toList());
        return new MenuTo(menu.getCreateDate(), dishes);
    }
}
