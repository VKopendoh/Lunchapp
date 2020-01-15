package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.AbstractTest;
import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static com.vkopendoh.lunchapp.RestaurantTestData.*;
import static com.vkopendoh.lunchapp.util.RestaurantUtil.getTo;
import static org.junit.Assert.assertEquals;

@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestaurantServiceTest extends AbstractTest {

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
        newRestaurant.setName(RESTAURANT_TO_SAVED.getName());
        Restaurant saved = service.save(newRestaurant);
        assertEquals(RESTAURANT_TO_SAVED, getTo(saved));
    }
}