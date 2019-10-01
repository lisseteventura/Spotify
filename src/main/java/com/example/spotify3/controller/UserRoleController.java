package com.example.spotify3.controller;

import com.example.spotify3.models.UserRole;
import com.example.spotify3.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleService roleService;

    @GetMapping("/{rolename}")
    public UserRole getRole(@PathVariable String rolename) {
        return roleService.getRole(rolename);
    }

    @PostMapping
    public UserRole createRole(@RequestBody UserRole role) {
        return roleService.createRole(role);
    }

}
