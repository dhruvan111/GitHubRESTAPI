package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.model.Order;
import com.example.GitHubRESTAPI.model.OrderProduct;
import com.example.GitHubRESTAPI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PostMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order updatedOrder) {
        return ResponseEntity.ok(orderService.updateOrder(updatedOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "id") String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable(name = "id") String customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/all/{date}")
    public ResponseEntity<List<Order>> getOrdersByDate(@PathVariable Date date) {
        return ResponseEntity.ok(orderService.getAllOrdersByDate(date));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<OrderProduct>> getProductsByOrderId(@PathVariable(name = "id") String orderId) {
        return ResponseEntity.ok(orderService.getAllProductsWithOrderId(orderId));
    }
}
