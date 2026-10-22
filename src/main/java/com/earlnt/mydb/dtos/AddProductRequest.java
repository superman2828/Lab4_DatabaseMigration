package com.earlnt.mydb.dtos;


import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
