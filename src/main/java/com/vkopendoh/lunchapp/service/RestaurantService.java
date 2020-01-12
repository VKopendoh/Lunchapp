package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        return restaurantRepository.getOne(id);
    }

    public Restaurant getFetch(int id) {
        return restaurantRepository.getWithMenu(id);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
}
