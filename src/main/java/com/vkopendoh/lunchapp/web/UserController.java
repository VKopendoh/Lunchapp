package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserController.REST_URL)
@Secured("ROLE_ADMIN")
public class UserController {

    @Autowired
    private UserService userService;

    final static String REST_URL = "/rest/user";

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @PostMapping
    public ResponseEntity<User> creteWithLocation(@RequestBody User user) {
        userService.save(user);
        User created = userService.getByName(user.getUsername());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}")
    public User update(@RequestBody User user, @PathVariable int id) {
        return userService.update(user, id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "User deleted with id:" + id;
    }


}
