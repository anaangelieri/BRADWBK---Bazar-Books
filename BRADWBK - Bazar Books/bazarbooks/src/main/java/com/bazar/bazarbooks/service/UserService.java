package com.bazar.bazarbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazar.bazarbooks.model.User;
import com.bazar.bazarbooks.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User putUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(int id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setIdUser(id);
            userRepository.save(updatedUser);
            return true;
        }
        return false;
    }
}
