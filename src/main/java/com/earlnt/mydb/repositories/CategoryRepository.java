package com.earlnt.mydb.repositories;

import com.earlnt.mydb.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}