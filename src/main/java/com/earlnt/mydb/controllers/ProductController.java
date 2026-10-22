package com.earlnt.mydb.controllers;


import com.earlnt.mydb.dtos.*;
import com.earlnt.mydb.entities.Product;
import com.earlnt.mydb.mappers.ProductMapper;
import com.earlnt.mydb.mappers.UserMapper;
import com.earlnt.mydb.repositories.CategoryRepository;
import com.earlnt.mydb.repositories.ProductRepository;
import com.earlnt.mydb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController

@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @GetMapping()
    public List<ProductDto> getAllProducts(@RequestParam(name = "categoryId", required = false) Long categoryId) {

        if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId).stream()
                    .map(product -> productMapper.toDto(product))
                    .toList();
        }
        return productRepository.findAll(Sort.by("id")).stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody AddProductRequest data){
        var product = productMapper.toEntity(data);
        product.setCategory(categoryRepository.findById(data.getCategoryId()).orElse(null));
        if (product.getCategory() == null) {
            return ResponseEntity.badRequest().build();
        }
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name="id") Long id,
            @RequestBody UpdateProductRequest data){

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setCategory(categoryRepository.findById(data.getCategoryId()).orElse(null));
        if (product.getCategory() == null) {
            return ResponseEntity.badRequest().build();
        }
        productMapper.update(data, product);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
