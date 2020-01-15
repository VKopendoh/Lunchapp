package com.vkopendoh.lunchapp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vkopendoh.lunchapp.model.*;
import com.vkopendoh.lunchapp.service.HistoryService;
import com.vkopendoh.lunchapp.service.MenuService;
import com.vkopendoh.lunchapp.service.RestaurantService;
import com.vkopendoh.lunchapp.service.UserService;
import com.vkopendoh.lunchapp.to.MenuTo;
import com.vkopendoh.lunchapp.to.MessageTo;
import com.vkopendoh.lunchapp.to.RestaurantTo;
import com.vkopendoh.lunchapp.util.MenuUtil;
import com.vkopendoh.lunchapp.util.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.vkopendoh.lunchapp.util.MenuUtil.getMenuCreatedDesc;
import static com.vkopendoh.lunchapp.util.MenuUtil.getMenuUpdatedDesc;
import static com.vkopendoh.lunchapp.util.RestaurantUtil.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL)
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    final static String REST_URL = "/rest/restaurant";

    @GetMapping(value = "/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return RestaurantUtil.getTo(restaurantService.getFetch(id));
    }

    @GetMapping
    public List<RestaurantTo> getALL() {
        return RestaurantUtil.getTos(restaurantService.getAll());
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        historyService.save(new History(Action.CREATE_RESTAURANT,
                "Restaurant created", getRestaurantDesc(created)), created);
        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @PutMapping(value = "/{id}/menu")
    @Transactional
    @Secured("ROLE_ADMIN")
    public MenuTo createOrUpdateMenu(@RequestBody Menu menu, @PathVariable int id) {
        Restaurant restaurant = restaurantService.get(id);
        Menu oldMenu = restaurant.getMenu();
        if (oldMenu == null) {
            menu.setCreateDate(LocalDate.now());
            historyService.save(new History(Action.CREATE_MENU,
                    getMenuCreatedDesc(menu), getRestaurantDesc(restaurant)), restaurant);
            restaurant.setMenu(menu);
            return MenuUtil.getTo(menuService.create(menu));
        }
        historyService.save(new History(Action.UPDATE_MENU,
                getMenuUpdatedDesc(oldMenu), getRestaurantDesc(restaurant)), restaurant);
        return MenuUtil.getTo(menuService.update(menu, oldMenu.getId()));
    }

    @PatchMapping(value = "/{id}/vote")
    @Transactional
    public Object vote(@PathVariable int id, @AuthenticationPrincipal Principal user) throws JsonProcessingException {
        Restaurant restaurant = restaurantService.get(id);
        User currentUser = userService.getByName(user.getName());
        if (LocalTime.now().compareTo(LocalTime.of(11, 0)) > 0) {
            return new MessageTo("You can vote only before 11:00 AM");
        }
        restaurant.addVoter(currentUser);
        historyService.save(new History(Action.VOTE,
                getVoteDesc(currentUser), getRestaurantDesc(restaurant)), restaurant);
        return getTo(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public Object delete(@PathVariable int id) {
        Restaurant restaurant = restaurantService.get(id);
        restaurantService.delete(id);
        String info = "Restaurant deleted with id:" + id;
        historyService.save(new History(Action.DELETE_RESTAURANT,
                info, getRestaurantDesc(restaurant)), restaurant);
        return new MessageTo(info);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public RestaurantTo update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        historyService.save(new History(Action.UPDATE_RESTAURANT,
                "Restaurant updated", getRestaurantDesc(restaurant)), restaurant);
        return getTo(restaurantService.update(restaurant, id));
    }
}
