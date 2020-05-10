package com.example.demo.user.mappers;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);

    @Mapping(target = "id", ignore = true)
    User map(String name);
    
    @Mapping(target = "id", ignore = true)
    User map(UserDto userDto, @MappingTarget User user);
}
