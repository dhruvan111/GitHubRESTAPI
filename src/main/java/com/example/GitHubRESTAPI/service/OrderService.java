package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.exception.OrderNotFound;
import com.example.GitHubRESTAPI.model.Order;
import com.example.GitHubRESTAPI.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class OrderService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public OrderService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Order createOrder(Order order) {
        double totalAmount = order.getTotalAmount();
        order.setTotalAmount(totalAmount);
        return mongoTemplate.save(order);
    }

    public Order updateOrder(Order updatedOrder) {
        Order order = mongoTemplate.findById(updatedOrder.getId(), Order.class);
        if (order == null) {
            return createOrder(updatedOrder);
        } else {
            mongoTemplate.remove(order);
        }
        double totalAmount = order.getTotalAmount();
        order.setTotalAmount(totalAmount);
        return mongoTemplate.save(order);
    }

    public Order getOrderById(String id) {
        return mongoTemplate.findById(id, Order.class);
    }

    public List<Order> getOrdersByCustomerId(String id) {
        Query query = new Query(Criteria.where("customer_id").is(id));
        return mongoTemplate.find(query, Order.class);
    }

    public List<Order> getAllOrdersByDate(Date date) {
        Query query = new Query(Criteria.where("order_date").is(date));
        return mongoTemplate.find(query, Order.class);
    }

    public List<OrderProduct> getAllProductsWithOrderId(String id) {
        Order order = mongoTemplate.findById(id, Order.class);
        if (order == null) {
            throw new OrderNotFound("Order NOT found with ID: " + id);
        }
        return order.getOrderProducts();
    }
}
