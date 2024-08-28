package com.example.GitHubRESTAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document("orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    @Field("order_date")
    private Date orderDate;

    @Field("total_amount")
    private double totalAmount;

    @Field("order_products")
    private List<OrderProduct> orderProducts;
}
