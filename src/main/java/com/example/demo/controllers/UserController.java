package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.CreateUserService;
import com.example.demo.services.DeleteUserByIdService;
import com.example.demo.services.GetAllUsersService;
import com.example.demo.services.GetUserByIdService;
import com.example.demo.services.SearchUsersByNameService;
import com.example.demo.services.UpdateUserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UpdateUserService updateUserService;
    private final SearchUsersByNameService searchUsersByNameService;
    private final CreateUserService createUserService;
    private final GetAllUsersService getAllUsersService;
    private final GetUserByIdService getUserByIdService;
    private final DeleteUserByIdService deleteUserByIdService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return getAllUsersService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("userId") long userId) {
        return getUserByIdService.getUserById(userId);
    }

    @GetMapping("/search")
    public List<UserDto> searchUsersByName(@RequestParam("name") String name) {
        return searchUsersByNameService.searchUsersByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserDto userDto) {
        createUserService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDto userDto) {
        userDto.setId(userId);
        updateUserService.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable("userId") Long userId) {
        deleteUserByIdService.deleteUserById(userId);
    }
}
