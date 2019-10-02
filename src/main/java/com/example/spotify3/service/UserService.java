package com.example.spotify3.service;

import com.example.spotify3.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//UserDetailsService is a way that SpringSecurity talks to the database
public interface UserService extends UserDetailsService {
    public Iterable<User> listUsers();
    //two methods for createUser
    //one method allows user to sign up
    //another method used when user signs up and will automatically get a token
    public String createUser(User newUser);
    public User login(String username, String password);
    public void deleteById(Long userId);
    public User addSong(String username, Long songId);
    public User getUser(String username);
    //method used when user logs in and will get a token
    public String login(User user);



}
