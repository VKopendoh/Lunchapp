package com.vkopendoh.lunchapp;

import com.vkopendoh.lunchapp.model.Dish;
import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.to.DishTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MenuTestData {

    public static final Integer MENU_ID = 1;
    public static final Integer DISH_ID = 1;
    public static final Integer MENU_ID_CREATED = MENU_ID + 1;

    public static final Dish DISH_ONE = new Dish("pizza", new BigDecimal(700));
    public static final Dish DISH_TWO = new Dish("cola", new BigDecimal(100));
    public static final Dish DISH_THREE = new Dish("burger", new BigDecimal(150));
    public static final Dish DISH_FOUR = new Dish("steak", new BigDecimal(4000));
    public static final Dish DISH_FIVE = new Dish("latte", new BigDecimal(300));
    public static final Dish DISH_SIX = new Dish("Fleur Burger", new BigDecimal(70000));

    public static final List<Dish> DISHES_OF_MENU_ONE = Arrays.asList(DISH_ONE, DISH_TWO, DISH_THREE);
    public static final List<Dish> DISHES_OF_MENU_TWO = Arrays.asList(DISH_FOUR, DISH_FIVE, DISH_SIX);

    public static Menu MENU_ONE = new Menu(LocalDate.of(2020, 1, 10), DISHES_OF_MENU_ONE);
    public static Menu MENU_TWO = new Menu(LocalDate.of(2020, 1, 11), DISHES_OF_MENU_TWO);


    public static Dish fromDishTo(DishTo dishTo) {
        Dish dish = new Dish();
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }
}
