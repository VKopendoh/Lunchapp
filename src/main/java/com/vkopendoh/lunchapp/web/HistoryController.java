package com.vkopendoh.lunchapp.web;

import com.vkopendoh.lunchapp.model.History;
import com.vkopendoh.lunchapp.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = HistoryController.REST_URL)
@Secured("ROLE_ADMIN")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    final static String REST_URL = "/rest/history";

    @GetMapping
    public List<History> getAll() {
        return historyService.getAll();
    }

    @GetMapping(value = "/{id}")
    public List<History> getByRestaurantId(@PathVariable int id) {
        return historyService.getByRestaurantId(id);
    }
}
