package com.vkopendoh.lunchapp.to;

import java.time.LocalDate;
import java.util.List;

public class MenuTo {
    private LocalDate createDate;
    private List<DishTo> dishes;

    public MenuTo() {
    }

    public MenuTo(LocalDate createDate, List<DishTo> dishes) {
        this.createDate = createDate;
        this.dishes = dishes;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<DishTo> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishTo> dishes) {
        this.dishes = dishes;
    }
}
