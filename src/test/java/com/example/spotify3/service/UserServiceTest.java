package com.example.spotify3.service;

import com.example.spotify3.config.JwtUtil;
import com.example.spotify3.models.User;
import com.example.spotify3.repository.SongRepository;
import com.example.spotify3.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private SongRepository songRepository;

    @Mock
    private JwtUtil jwtUtil;


    @InjectMocks
    private User user;

    @Before
    public void initializeDummyUser(){
        user.setUsername("batman");
        user.setPassword("robin");
    }

    @Test
    public void getUser_ReturnsUser_Success(){

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User tempUser = userService.getUser(user.getUsername());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }

    @Test
    public void login_UserNotFound_Error(){

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        String token = userService.login(user);

        assertEquals(token, null);
    }
}