package com.example.GitHubRESTAPI.authentication.dblayer;

import com.example.GitHubRESTAPI.authentication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}