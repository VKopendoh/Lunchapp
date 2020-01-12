package com.vkopendoh.lunchapp.util;

import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.MenuTo;
import com.vkopendoh.lunchapp.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {
    public static RestaurantTo getTo(Restaurant restaurant) {
        MenuTo menu = restaurant.getMenu() == null ? null : MenuUtil.getTo(restaurant.getMenu());
        Integer voters = restaurant.getVoters() == null ? 0 : restaurant.getVoters().size();
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), menu, voters);
    }

    public static List<RestaurantTo> getTos(List<Restaurant> list) {
        return list == null ? null : list.stream()
                .map(RestaurantUtil::getTo).collect(Collectors.toList());
    }
}
