package com.vkopendoh.lunchapp;

import com.vkopendoh.lunchapp.model.Dish;
import com.vkopendoh.lunchapp.model.Menu;
import com.vkopendoh.lunchapp.model.Role;
import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.to.DishTo;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserTestData {

    public static final Integer USER_ID = 1;
    public static final Integer ADMIN_ID = USER_ID + 2;
    public static final Integer NEW_USER_ID = ADMIN_ID + 1;
    public static final Integer USERS_COUNT = ADMIN_ID;

    public static final User USER =
            new User(1, "user", "pass", true, new Date(), Arrays.asList(Role.ROLE_USER));
    public static final User ADMIN =
            new User(3, "admin", "admin", true, new Date(), Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));

    public static final User NEW_USER =
            new User(NEW_USER_ID, "newUser", "pass", true, new Date(), Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));

    public static final String WRONG_NAME = "IVAN";
}
