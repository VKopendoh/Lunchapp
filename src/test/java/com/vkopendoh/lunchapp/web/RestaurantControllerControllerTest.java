package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.AbstractControllerTest;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.vkopendoh.lunchapp.RestaurantTestData.RESTAURANT_ONE;
import static org.junit.Assert.assertEquals;

public class RestaurantControllerControllerTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void get() throws Exception {
        String uri = RestaurantController.REST_URL + "/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        RestaurantTo restaurant = super.mapFromJson(content, RestaurantTo.class);
        RestaurantTo expected = RESTAURANT_ONE;
        assertEquals(restaurant, expected);

    }

    @Test
    public void createWithLocation() {
    }

    @Test
    public void createOrUpdateMenu() {
    }

    @Test
    public void vote() {

    }


}