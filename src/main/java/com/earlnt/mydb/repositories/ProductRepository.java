package com.earlnt.mydb.repositories;

import com.earlnt.mydb.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}