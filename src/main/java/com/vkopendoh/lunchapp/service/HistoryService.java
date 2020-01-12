package com.vkopendoh.lunchapp.service;


import com.vkopendoh.lunchapp.model.History;
import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.repository.HistoryRepository;
import com.vkopendoh.lunchapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<History> getByRestaurantId(int id) {
        return historyRepository.findAllByRestaurantId(id);
    }

    public void save(History history, Restaurant restaurant) {
        history.setRestaurant(restaurant);
        historyRepository.save(history);
    }

}
