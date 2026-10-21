package com.earlnt.mydb.dtos;

import com.earlnt.mydb.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
