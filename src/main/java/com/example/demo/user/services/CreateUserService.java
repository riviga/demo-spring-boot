package com.example.demo.user.services;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;
import com.example.demo.user.mappers.UserMapper;
import com.example.demo.user.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Transactional
    public void createUser(@NonNull UserDto userDto) {
        notNull(userDto.getName(), "El nombre no puede ser nulo");
        User user = userMapper.map(userDto.getName()); 
        userRepository.save(user);
    }
}
