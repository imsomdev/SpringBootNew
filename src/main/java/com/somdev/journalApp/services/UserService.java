package com.somdev.journalApp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.somdev.journalApp.entity.User;
import com.somdev.journalApp.repository.UserRepository;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAllEntry() {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            // Log the exception or handle it as per your application's requirements
            e.printStackTrace(); // Example of logging the exception
            // You can throw a custom exception or return an empty list depending on your use case
            return Collections.emptyList();
        }
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
