package com.vkopendoh.lunchapp.to;

public class RestaurantTo {
    private String name;
    private MenuTo menu;
    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(String name, MenuTo menu, Integer votes) {
        this.name = name;
        this.menu = menu;
        this.votes = votes;
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
}
