package com.example.demo.user;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;

public class UserMother {

    public UserDto defaultNameUserDto() {
        return new UserDto(1L, "no-name");
    }

    public UserDto johnDto() {
        return new UserDto(1L, "John");
    }

    public UserDto john2Dto() {
        return new UserDto(2L, "John");
    }

    public UserDto john3Dto() {
        return new UserDto(3L, "John");
    }

    public UserDto peterDto() {
        return new UserDto(4L, "Peter");
    }

    public UserDto notExistUser() {
        return new UserDto(0L, "No exist");
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
        return new UserDto(null, "John");
    }

    public UserDto userWithNoName() {
        return new UserDto(0L, null);
    }
}
