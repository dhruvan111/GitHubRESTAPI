package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.exception.ProductNotFoundException;
import com.example.GitHubRESTAPI.model.Category;
import com.example.GitHubRESTAPI.model.Product;
import com.example.GitHubRESTAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(MongoTemplate mongoTemplate, ProductRepository productRepository) {
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
    }

    public Product getProductById(String productId) {
        Product product = mongoTemplate.findById(productId, Product.class);
        if (product == null) {
            throw new ProductNotFoundException("Product NOT found with ID: " + productId);
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String categoryId) {
        Query query = new Query(Criteria.where("category_id").is(categoryId));
        return mongoTemplate.find(query, Product.class);
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        Query query1 = new Query(Criteria.where("name").is(categoryName));
        List<Category> categories = mongoTemplate.find(query1, Category.class);
        Category category = categories.get(0);
        return getProductsByCategory(category.getId());
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProductById(String id) {
        Product product = mongoTemplate.findById(id, Product.class);
        if (product != null) {
            mongoTemplate.remove(product);
            return true;
        }
        return false;
    }
}
