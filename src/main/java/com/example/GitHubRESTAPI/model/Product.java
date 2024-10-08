package com.example.GitHubRESTAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    private String id;

    private String name;
    private String description;
    private double price;

    @Field("category_id")
    private String categoryId;
}
