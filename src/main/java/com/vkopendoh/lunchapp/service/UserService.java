package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.model.Restaurant;
import com.vkopendoh.lunchapp.model.Role;
import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.repository.UserRepository;
import com.vkopendoh.lunchapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll(Sort.by(Sort.Order.asc("name")));
    }

    public User get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public User getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("User not found with name: " + name));
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("UserDetails not found for User with name: " + name));
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional
    public User update(User user, int id) {
        User updated = get(id);
        Restaurant restaurant = user.getRestaurant();
        if (restaurant != null) {
            updated.setRestaurant(restaurant);
        }
        Set<Role> roles = user.getRoles();
        if (roles != null && roles.size() > 0) {
            updated.setRoles(roles);
        }
        String password = user.getPassword();
        if (password != null && password.trim().length() > 3) {
            updated.setPassword(password);
        }
        String name = user.getUsername();
        if (name != null && name.trim().length() > 3) {
            updated.setName(name);
        }
        repository.save(updated);
        return updated;
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}

