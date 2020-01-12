package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.model.*;
import com.vkopendoh.lunchapp.service.HistoryService;
import com.vkopendoh.lunchapp.service.MenuService;
import com.vkopendoh.lunchapp.service.RestaurantService;
import com.vkopendoh.lunchapp.service.UserService;
import com.vkopendoh.lunchapp.to.MenuTo;
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
import java.util.List;

import static com.vkopendoh.lunchapp.util.MenuUtil.getMenuCreatedDesc;
import static com.vkopendoh.lunchapp.util.RestaurantUtil.getRestaurantDesc;

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
        return RestaurantUtil.getTo(restaurantService.get(id));
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
        return ResponseEntity.created(uriOfNewResource).body(created);

    }

    @PutMapping(value = "/{id}/menu")
    @Transactional
    @Secured("ROLE_ADMIN")
    public MenuTo createOrUpdateMenu(@RequestBody Menu menu, @PathVariable int id) {
        Restaurant restaurant = restaurantService.getFetch(id);
        Menu oldMenu = restaurant.getMenu();
        if (oldMenu == null) {
            menu.setCreateDate(LocalDate.now());
            historyService.save(new History(Action.CREATE_MENU,
                    getMenuCreatedDesc(menu), getRestaurantDesc(restaurant)), restaurant);
            restaurant.setMenu(menu);
            return MenuUtil.getTo(menuService.create(menu));
        }
        return MenuUtil.getTo(menuService.update(menu, oldMenu.getId()));
    }

    @PatchMapping(value = "/{id}/vote")
    @Transactional
    public RestaurantTo vote(@PathVariable int id, @AuthenticationPrincipal Principal user) {
        Restaurant restaurant = restaurantService.get(id);
        User currentUser = userService.getByName(user.getName());
        /*if (LocalTime.now().compareTo(LocalTime.of(19, 0)) < 0) {
            return RestaurantUtil.getTo(restaurant);
        }*/
        currentUser.setRestaurant(restaurant);
        userService.save(currentUser);
        return RestaurantUtil.getTo(restaurant);
    }


}
