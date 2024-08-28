package com.example.GitHubRESTAPI.repository;

import com.example.GitHubRESTAPI.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
