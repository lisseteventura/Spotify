package com.example.spotify3.service;

import com.example.spotify3.models.Song;
import com.example.spotify3.models.User;
import com.example.spotify3.models.UserRole;
import com.example.spotify3.repository.SongRepository;
import com.example.spotify3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SongRepository songRepository;
    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }
    @Override
    public User createUser(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        return userRepository.save(newUser);
    }
    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    //users add song by using username and songId
    @Override
    public User getUser(String username){
        return userRepository.findByUsername(username);
    }
    @Override
    public User addSong(String username, Long songId) {
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.addSong(song);

        return userRepository.save(user);
    }
}
