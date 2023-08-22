package com.microservice.user.service.controllers;

import com.microservice.user.service.entities.User;
import com.microservice.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user=this.userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId,@RequestBody User user){
        User updatedUser=this.userService.updateUser(user,userId);
        return  ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleUser(@PathVariable String userId){
        User deletedUser=this.userService.deleteUser(userId);
        return new ResponseEntity<>(deletedUser,HttpStatus.GONE);
    }

}
