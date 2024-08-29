package com.example.GitHubRESTAPI.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    MongoClient getMongoClient() {
        return MongoClients.create(uri);
    }

    @Bean
    MongoTemplate getMongoTemplate() {
        return new MongoTemplate(getMongoClient(), database);
    }
}
