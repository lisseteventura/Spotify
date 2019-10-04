package com.example.spotify3.config;

import com.example.spotify3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//this is an analog for xml file. it will be use to configure our application with security features
@Configuration
//allows Spring to find configuration class and automatically apply the class to the global WebSecurity.
@EnableWebSecurity
//WebSecurityConfigurerAdapter is extended to enable HttpSecurity in our app.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    //jwt sends out a request to get the token
    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    //configure http request, we do this by overriding another method
    //This method ensures that all requests are authenticated with Http based authentication.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf is an attack that forces an end user to execute unwanted actions on a web application in which they're currently authenticated.
        //csrf is when someone hijacks your token, this method is so that that doesnt happen
        //antMatchers allows configuring the HttpSecurity to only be invoked when matching the provided ant pattern
        //http.antMatcher states that this HttpSecurity will only be applicable to URLs that start with /user/ ../role.. etc
        // anything that starts with /user/**
        //hasRole() method first authenticates the User then checks its role against the authorized Role.
        //permitAll() permits the requests to the given endpoints without authenticating them.
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup/**", "/login/**").permitAll()
                .antMatchers("/user/**", "/profile/**", "/songs/**").authenticated()
                .antMatchers("/role/**").hasRole("DBA")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .httpBasic();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //in memory authentication-instead of getting user credentials from database we'll hardcode username and password in the code
    //Using the builder pattern we can create multiple users with different attributes, authorities and roles
    //added an antMatchers that hasRole of DBA so we've also have to add it to our configure method
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        // Uses a default bCrypt password encoder. Not defined by your jwt.secret token.
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication().withUser("test").password(encoder.encode("test")).roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password(encoder.encode("dba")).roles("DBA");
//    }

}
