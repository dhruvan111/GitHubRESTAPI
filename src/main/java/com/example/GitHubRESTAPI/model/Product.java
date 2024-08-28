package com.example.GitHubRESTAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "product")
@Getter
@Setter
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
