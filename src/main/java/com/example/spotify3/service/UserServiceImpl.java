package com.example.spotify3.service;

import com.example.spotify3.models.User;
import com.example.spotify3.models.UserRole;
import com.example.spotify3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;
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
}
