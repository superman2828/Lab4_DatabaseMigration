package com.earlnt.mydb.services;

import com.earlnt.mydb.entities.Category;
import com.earlnt.mydb.entities.Product;
import com.earlnt.mydb.repositories.CategoryRepository;
import com.earlnt.mydb.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    @Transactional
    public void newCategory() {
        var category = Category.builder()
                .name("Electronics")
                .build();
        categoryRepository.save(category);

        var product = Product.builder()
                .name("Camera")
                .description("A high-end camera")
                .price(new BigDecimal("999.99"))
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void existingCategory() {
        var category = categoryRepository.findById(1L).orElseThrow();
        var product = Product.builder()
                .name("Laptop")
                .description("A powerful laptop")
                .price(new BigDecimal("1499.99"))
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void deleteRelated() {
        var product = productRepository.findById(2L).orElseThrow();
        productRepository.delete(product);
    }

}
