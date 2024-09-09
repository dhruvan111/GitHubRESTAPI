package com.example.GitHubRESTAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    @Field("customer_id")
    private String customerId;

    @Field("order_date")
    private Date orderDate;

    @Field("total_amount")
    private double totalAmount;

    @Field("order_products")
    private List<OrderProduct> orderProducts;

    public double calculateTotal() {
        double total = 0;
        for (OrderProduct product:orderProducts) {
            total += (product.getProductPrice()) * (product.getQuantity());
        }
        return total;
    }
}
