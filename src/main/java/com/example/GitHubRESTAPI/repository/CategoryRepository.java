package com.example.GitHubRESTAPI.repository;

import com.example.GitHubRESTAPI.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
