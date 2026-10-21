package com.earlnt.mydb.mappers;

import com.earlnt.mydb.dtos.UserDto;
import com.earlnt.mydb.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
