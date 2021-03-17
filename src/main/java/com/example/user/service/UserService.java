package com.example.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.domain.User;
import com.example.user.repository.UserRepository;

@Service
public class UserService {
    // ①
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}