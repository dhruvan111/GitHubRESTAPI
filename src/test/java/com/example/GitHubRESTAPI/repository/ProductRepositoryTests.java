package com.example.GitHubRESTAPI.repository;

import com.example.GitHubRESTAPI.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.util.Assert;

@DataMongoTest
class ProductRepositoryTests {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTests(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    void productRepository_saveProduct_returnSavedProduct() {
        Product product = new Product("2442", "laptop", "it is laptop", 34553, "2432");
        Product savedProduct = productRepository.save(product);
        Assert.notNull(savedProduct, "Saved Product should NOT be NULL.");
    }
}
