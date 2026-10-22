package com.earlnt.mydb.mappers;

import com.earlnt.mydb.dtos.RegisterUserRequest;
import com.earlnt.mydb.dtos.UpdateUserRequest;
import com.earlnt.mydb.dtos.UserDto;
import com.earlnt.mydb.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request,@MappingTarget User user);
}
