package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.AbstractTest;
import com.vkopendoh.lunchapp.model.Dish;
import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.to.MenuTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.vkopendoh.lunchapp.MenuTestData.*;
import static com.vkopendoh.lunchapp.util.MenuUtil.getTo;
import static org.junit.Assert.assertEquals;

@Transactional
public class MenuServiceTest extends AbstractTest {

    @Autowired
    private MenuService service;

    @Test
    public void get() {
        MenuTo menu = getTo(service.get(MENU_ID));
        assertEquals(menu, getTo(MENU_ONE));
    }

    @Test
    public void create() {
        Menu menu = new Menu(LocalDate.of(2020, 1, 10), DISHES_OF_MENU_ONE);
        assertEquals(getTo(service.create(menu)), getTo(MENU_ONE));
    }

    @Test
    public void update() {
        List<Dish> newDishes = new ArrayList<>();
        newDishes.addAll(Arrays.asList(DISH_FOUR, DISH_FIVE, DISH_SIX));
        Menu newMenu = new Menu(null, newDishes);
        Menu updated = service.update(newMenu, MENU_ID);
        MENU_TWO.setCreateDate(LocalDate.now());
        MenuTo expected = getTo(MENU_TWO);
        assertEquals(expected, getTo(updated));
    }
}