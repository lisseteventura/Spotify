package com.example.spotify3.repository;

import com.example.spotify3.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    public UserRole findByName(String name);

}
