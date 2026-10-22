package com.earlnt.mydb.mappers;


import com.earlnt.mydb.dtos.AddProductRequest;
import com.earlnt.mydb.dtos.ProductDto;
import com.earlnt.mydb.dtos.UpdateProductRequest;
import com.earlnt.mydb.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(AddProductRequest request);

    void update(UpdateProductRequest request, @MappingTarget Product product);
}
