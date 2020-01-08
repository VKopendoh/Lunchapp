package com.vkopendoh.lunchapp.service;

import com.vkopendoh.lunchapp.model.User;
import com.vkopendoh.lunchapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.vkopendoh.lunchapp.util.ValidationUtil.checkNotFoundWithId;

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
        Optional<User> user = repository.findById(id);
        return checkNotFoundWithId(user.orElseGet(null), id);
    }

    public User getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return repository.findByName(name);
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}

