package com.example.demo.user.services;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;
import com.example.demo.user.mappers.UserMapper;
import com.example.demo.user.repositories.UserRepository;
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
        return userMapper.map(user);
    }
}
