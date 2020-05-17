package com.example.demo.user;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;

public class UserMother {

    public UserDto defaultNameUserDto() {
        return userDto(1L, "no-name");
    }

    public UserDto johnDto() {
        return userDto(1L, "John");
    }

    public UserDto john2Dto() {
        return userDto(2L, "John");
    }

    public UserDto john3Dto() {
        return userDto(3L, "John");
    }

    public UserDto peterDto() {
        return userDto(4L, "Peter");
    }

    public UserDto notExistUser() {
        return userDto(0L, "No exist");
    }

    public User john() {
        return new User(1L, "John");
    }

    public User john2() {
        return new User(2L, "John");
    }

    public User john3() {
        return new User(3L, "John");
    }

    public User peter() {
        return new User(4L, "Peter");
    }

    public User defaultNameUser() {
        return new User(1L, "no-name");
    }

    public UserDto userWithNoId() {
        return userDto(null, "John");
    }

    public UserDto userWithNoName() {
        return userDto(0L, null);
    }

    private UserDto userDto(Long id, String name) {
        return UserDto.builder().id(id).name(name).build();
    }
}
