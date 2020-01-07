package com.vkopendoh.lunchapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Menu extends AbstractBaseEntity {

    @Column(columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate createDate;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    @OneToOne(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restaurant restaurant;

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}