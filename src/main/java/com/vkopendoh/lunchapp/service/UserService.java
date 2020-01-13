package com.vkopendoh.lunchapp.service;

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
    public void delete(int id) {
        repository.deleteById(id);
    }
}

