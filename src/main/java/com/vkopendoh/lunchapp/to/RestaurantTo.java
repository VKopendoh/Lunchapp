package com.vkopendoh.lunchapp.to;

import java.util.Objects;

public class RestaurantTo {
    private Integer id;
    private String name;
    private MenuTo menu;
    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, MenuTo menu, Integer votes) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuTo getMenu() {
        return menu;
    }

    public void setMenu(MenuTo menu) {
        this.menu = menu;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantTo)) return false;
        RestaurantTo that = (RestaurantTo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(menu, that.menu) &&
                Objects.equals(votes, that.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, menu, votes);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", votes=" + votes +
                '}';
    }
}
