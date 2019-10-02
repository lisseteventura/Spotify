package com.example.spotify3.controller;

import com.example.spotify3.models.JwtResponse;
import com.example.spotify3.models.User;
import com.example.spotify3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //all requests to /user path should not be authorized, but only ADMIN should be able to fetch all users
    @GetMapping("/admin/user/list")
    public Iterable<User> listUsers() { return userService.listUsers();
    }
//    @PostMapping("/signup")
//    public String createUser(@RequestBody User newUser) {
//        return userService.createUser(newUser);
//    }
    //allows users to login
    @GetMapping("/login/{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password) {
        return userService.login(username, password);
    }
    //used to delete users from table
    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    //allows users to add songs
    @PutMapping("/user/{username}/{songId}")
    public User addSong(@PathVariable String username, @PathVariable Long songId){
        return userService.addSong(username, songId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }


}
