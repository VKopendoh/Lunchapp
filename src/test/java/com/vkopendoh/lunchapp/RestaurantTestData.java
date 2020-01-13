package com.vkopendoh.lunchapp;

import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import com.vkopendoh.lunchapp.util.MenuUtil;

import static com.vkopendoh.lunchapp.MenuTestData.MENU_ONE;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = 1;
    public static final int RESTAURANT_SAVED_ID = RESTAURANT_ID + 2;

    public static final RestaurantTo RESTAURANT_ONE =
            new RestaurantTo(RESTAURANT_ID, "Rostov burgers", MenuUtil.getTo(MENU_ONE), 0);
    public static final RestaurantTo RESTAURANT_TO_SAVED = new RestaurantTo(RESTAURANT_SAVED_ID, "NEW RESTAURANT",
            null, 0);

    public static final Restaurant RESTAURANT_SAVED = new Restaurant(RESTAURANT_SAVED_ID, "NEW RESTAURANT",
            null, null, null);
}
