package com.microservice.user.service.services;

import com.microservice.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    User updateUser(User user,String userId);
    User deleteUser(String userId);
}
