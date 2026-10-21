package com.earlnt.mydb.mappers;


import com.earlnt.mydb.dtos.ProductDto;
import com.earlnt.mydb.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
