package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.AbstractControllerTest;
import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.to.MessageTo;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import com.vkopendoh.lunchapp.util.JsonUtil;
import com.vkopendoh.lunchapp.util.MenuUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.vkopendoh.lunchapp.MenuTestData.MENU_TWO;
import static com.vkopendoh.lunchapp.RestaurantTestData.RESTAURANT_ONE;
import static com.vkopendoh.lunchapp.RestaurantTestData.RESTAURANT_SAVED;
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
        RestaurantTo restaurant = JsonUtil.mapFromJson(content, RestaurantTo.class);
        RestaurantTo expected = RESTAURANT_ONE;
        assertEquals(restaurant, expected);

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createWithLocation() throws Exception {
        String uri = RestaurantController.REST_URL;
        String inputJson = JsonUtil.mapToJson(RESTAURANT_SAVED);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        Restaurant actual = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), Restaurant.class);
        assertEquals(RESTAURANT_SAVED, actual);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createOrUpdateMenu() throws Exception {
        String uri = RestaurantController.REST_URL + "/1/menu";
        MENU_TWO.setCreateDate(LocalDate.now());
        String inputJson = JsonUtil.mapToJson(MenuUtil.getTo(MENU_TWO));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(JsonUtil.mapToJson(MenuUtil.getTo(MENU_TWO)), content);
    }

    @Test
    public void vote() throws Exception {
        String uri = RestaurantController.REST_URL + "/1/vote";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri).principal(new UserPrincipal() {
            @Override
            public String getName() {
                return "user";
            }
        })
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        if (LocalTime.now().compareTo(LocalTime.of(11, 0)) > 0) {
            MessageTo actualMsg = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), MessageTo.class);
            assertEquals(new MessageTo("You can vote only before 11:00 AM"), actualMsg);
            return;
        }
        RestaurantTo actual = JsonUtil.mapFromJson(mvcResult.getResponse().getContentAsString(), RestaurantTo.class);
        RESTAURANT_ONE.setVotes(RESTAURANT_ONE.getVotes() + 1);
        assertEquals(RESTAURANT_ONE, actual);
    }
}