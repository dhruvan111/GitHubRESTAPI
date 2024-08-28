package com.example.GitHubRESTAPI.repository;

import com.example.GitHubRESTAPI.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
