package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserByIdService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto getUserById(@NonNull Long userId) {
        User user = userRepository.getOne(userId);
        UserDto userDto = userMapper.map(user);
        return userDto;
    }
}
