package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.AbstractTest;
import com.vkopendoh.lunchapp.model.Role;
import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.vkopendoh.lunchapp.UserTestData.*;
import static org.junit.Assert.*;

@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void getAll() {
        assertTrue(userService.getAll().size() == USERS_COUNT);
    }

    @Test
    public void get() {
        User actual = userService.get(USER_ID);
        assertEquals(USER, actual);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        userService.get(NEW_USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getByNameNotFound() {
        userService.getByName(WRONG_NAME);
    }


    @Test
    public void getByName() {
        User actual = userService.getByName(USER.getUsername());
        assertEquals(USER, actual);
    }

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = userService.loadUserByUsername(USER.getUsername());
        assertEquals(USER.getPassword(), userDetails.getPassword());
        assertEquals(USER.getUsername(), userDetails.getUsername());
    }

    @Test
    public void save() {
        userService.save(new User(NEW_USER_ID, "newUser", "pass", true, new Date(),
                Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        User actual = userService.get(NEW_USER_ID);
        assertEquals(NEW_USER, actual);
    }

    @Test
    public void delete() {
        userService.delete(USER_ID);
        assertTrue(userService.getAll().size() == USERS_COUNT - 1);
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void deleteNotFound(){
        userService.delete(NEW_USER_ID);
    }
}