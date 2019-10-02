package com.example.spotify3.service;

import com.example.spotify3.models.User;

public interface UserService {
    public Iterable<User> listUsers();
    public User createUser(User newUser);
    public User login(String username, String password);
    public void deleteById(Long userId);
    public User addSong(String username, Long songId);

    public User getUser(String username);
}
