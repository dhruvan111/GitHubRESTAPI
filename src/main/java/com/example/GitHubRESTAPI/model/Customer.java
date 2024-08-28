package com.example.GitHubRESTAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private String id;

    private List<Order> orders;
}
