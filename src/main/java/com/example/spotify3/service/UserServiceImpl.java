package com.example.spotify3.service;

import com.example.spotify3.config.JwtUtil;
import com.example.spotify3.models.Song;
import com.example.spotify3.models.User;
import com.example.spotify3.models.UserRole;
import com.example.spotify3.repository.SongRepository;
import com.example.spotify3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SongRepository songRepository;

    //this method loads user information using the username
    //loadUserByUsername() is called by Spring Security by default to to check against the User data.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if(user==null)
            throw new UsernameNotFoundException("User null");
        // Code edited to not include bCrypt
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
        return authorities;
    }

    //method used when user logs in and will get a token
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String login(User user){
        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }


    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }


    //allows user to sign up and get token
    @Override
    public String createUser(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(newUser.getPassword());
        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
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
