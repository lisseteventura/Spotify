package com.example.spotify3.service;

import com.example.spotify3.models.UserRole;

public interface UserRoleService {

    public UserRole createRole(UserRole newRole);

    public UserRole getRole(String roleName);
}
