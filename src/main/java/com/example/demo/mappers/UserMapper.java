package com.example.demo.mappers;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
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
