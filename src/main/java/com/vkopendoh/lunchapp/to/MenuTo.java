package com.vkopendoh.lunchapp.to;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuTo)) return false;
        MenuTo menuTo = (MenuTo) o;
        return Objects.equals(createDate, menuTo.createDate) &&
                Objects.equals(dishes, menuTo.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate, dishes);
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "createDate=" + createDate +
                ", dishes=" + dishes +
                '}';
    }
}
