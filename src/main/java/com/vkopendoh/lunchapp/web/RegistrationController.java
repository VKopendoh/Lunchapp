package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.model.Role;
import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.service.UserService;
import com.vkopendoh.lunchapp.to.MessageTo;
import com.vkopendoh.lunchapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Arrays;

@RestController
@RequestMapping(value = RegistrationController.REST_URL)
public class RegistrationController {

    @Autowired
    private UserService userService;

    final static String REST_URL = "/rest/registration";

    @GetMapping(value = "/info")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public User get(@AuthenticationPrincipal Principal user) {
        return userService.getByName(user.getName());
    }

    @PostMapping
    public ResponseEntity<User> creteWithLocation(@RequestBody User user) {
        User newUser = new User();
        newUser.setName(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(Arrays.asList(Role.ROLE_USER));
        userService.save(newUser);
        User created = userService.getByName(user.getUsername());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/update")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public User update(@RequestBody User user, @AuthenticationPrincipal Principal currentUser) {
        User updated = userService.getByName(currentUser.getName());
        user.setRoles(updated.getRoles());
        Integer id = updated.getId();
        if (id == null) {
            throw new NotFoundException("User not exist with:");
        }
        return userService.update(user, id);
    }

    @DeleteMapping(value = "/delete")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Object delete(@AuthenticationPrincipal Principal currentUser) {
        User user = userService.getByName(currentUser.getName());
        Integer id = user.getId();
        if (id == null) {
            throw new NotFoundException("User not exist with:");
        }
        userService.delete(id);
        return new MessageTo("User deleted with id:" + id);
    }
}
