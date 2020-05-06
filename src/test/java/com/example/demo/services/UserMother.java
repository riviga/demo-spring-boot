package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;

public class UserMother {

    public UserDto defaultNameUserDto() {
        return new UserDto(1L, "no-name");
    }

    public UserDto johnDto() {
        return new UserDto(1L, "John");
    }

    public UserDto notExistUser() {
        return new UserDto(0L, "No exist");
    }

    public User john() {
        return new User(1L, "John");
    }

    public User defaultNameUser() {
        return new User(1L, "no-name");
    }

    public UserDto userWithNoId() {
        return new UserDto(null, "John");
    }
    
    public UserDto userWithNoName() {
        return new UserDto(0L, null);
    }
}
