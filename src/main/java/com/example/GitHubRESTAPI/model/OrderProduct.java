package com.example.GitHubRESTAPI.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderProduct {
    @Field("product_id")
    private String productId;

    @Field("product_name")
    private String productName;

    @Field("product_price")
    private double productPrice;

    private int quantity;
}
