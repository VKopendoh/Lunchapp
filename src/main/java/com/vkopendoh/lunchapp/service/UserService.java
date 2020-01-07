package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.vkopendoh.lunchapp.util.ValidationUtil.checkNew;
import static com.vkopendoh.lunchapp.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll(Sort.by(Sort.Order.asc("name")));
    }

    public User get(int id) {
        Optional<User> user = repository.findById(id);
        return checkNotFoundWithId(user.orElseGet(null), id);
    }

    @Transactional
    public User save(User user) {
        checkNew(user);
        repository.save(user);
        return user;
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}

