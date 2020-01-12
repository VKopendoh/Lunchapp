package com.vkopendoh.lunchapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.to.DishTo;
import com.vkopendoh.lunchapp.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

import static com.vkopendoh.lunchapp.util.JsonUtil.mapToJson;

public class MenuUtil {
    public static MenuTo getTo(Menu menu) {
        List<DishTo> dishes = menu.getDishes() == null ? null : menu.getDishes()
                .stream()
                .map(dish -> new DishTo(dish.getName(), dish.getPrice()))
                .collect(Collectors.toList());
        return new MenuTo(menu.getCreateDate(), dishes);
    }

    public static String getMenuCreatedDesc(Menu menu) {
        return "Menu created: " + getMenuDesc(menu);
    }

    public static String getMenuDesc(Menu menu) {
        String menuString = null;
        try {
            menuString = mapToJson(getTo(menu));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return menuString;
    }
}
