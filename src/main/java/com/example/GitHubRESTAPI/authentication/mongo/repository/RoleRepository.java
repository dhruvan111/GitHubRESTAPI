package com.example.GitHubRESTAPI.authentication.mongo.repository;

import com.example.GitHubRESTAPI.authentication.entity.ERole;
import com.example.GitHubRESTAPI.authentication.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
