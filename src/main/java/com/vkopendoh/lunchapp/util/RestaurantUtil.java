package com.vkopendoh.lunchapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.to.MenuTo;
import com.vkopendoh.lunchapp.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

import static com.vkopendoh.lunchapp.util.JsonUtil.mapToJson;

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

    public static String restaurantCreateDesc(Restaurant restaurant) {
        return "R" + getRestaurantDesc(restaurant);
    }

    public static String getRestaurantDesc(Restaurant restaurant) {
        String restaurantString = null;
        try {
            restaurantString = mapToJson(getTo(restaurant));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return restaurantString;
    }

    public static String getVoteDesc(User currentUser) throws JsonProcessingException {
        return "User: " + JsonUtil.mapToJson(currentUser) + "voted for restaurant";
    }
}
