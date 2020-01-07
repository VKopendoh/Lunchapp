package com.vkopendoh.lunchapp.util;

import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.MenuTo;
import com.vkopendoh.lunchapp.to.RestaurantTo;

public class RestaurantUtil {
    public static RestaurantTo getTo(Restaurant restaurant) {
        MenuTo menu = MenuUtil.getTo(restaurant.getMenu());
        return new RestaurantTo(restaurant.getName(), menu, menu.getDishes().size());
    }
}
