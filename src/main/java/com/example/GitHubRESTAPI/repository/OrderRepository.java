package com.example.GitHubRESTAPI.repository;

import com.example.GitHubRESTAPI.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
