package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.LunchApplication;
import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.vkopendoh.lunchapp.RestaurantTestData.*;
import static com.vkopendoh.lunchapp.util.RestaurantUtil.getTo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LunchApplication.class)
@Transactional
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void get() {
        RestaurantTo restaurant = getTo(service.get(RESTAURANT_ID));
        assertEquals(RESTAURANT_ONE, restaurant);
    }

    @Test
    public void save() {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(RESTAURANT_SAVED.getName());
        Restaurant saved = service.save(newRestaurant);
        assertEquals(RESTAURANT_SAVED, getTo(saved));
    }
}