package com.vkopendoh.lunchapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class History extends AbstractBaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Action action;

    private String description;

    private String restaurantInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime actionTime;

    public History(@NotNull Action action, String description, String restaurantInfo) {
        this.action = action;
        this.description = description;
        this.restaurantInfo = restaurantInfo;
        this.actionTime = LocalDateTime.now();
    }

    public String getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(String restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }
}
