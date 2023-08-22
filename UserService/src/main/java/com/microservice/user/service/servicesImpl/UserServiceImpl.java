package com.microservice.user.service.servicesImpl;

import com.microservice.user.service.entities.User;
import com.microservice.user.service.exceptions.ResourceNotFoundException;
import com.microservice.user.service.repositories.UserRepository;
import com.microservice.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
       String randomId= UUID.randomUUID().toString();
       user.setUserId(randomId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found with UserId : "+userId));
    }

    @Override
    public User updateUser(User user, String userId) {
       User userFromDb= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found with UserId : "+userId));
       user.setUserId(userId);
       userFromDb.setName(user.getName());
       userFromDb.setEmail(user.getEmail());
       userFromDb.setAbout(user.getAbout());

        return this.userRepository.save(userFromDb) ;
    }

    @Override
    public User deleteUser(String userId) {
        User userFromDb= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found with UserId : "+userId));
        this.userRepository.delete(userFromDb);
        return userFromDb ;
    }
}
