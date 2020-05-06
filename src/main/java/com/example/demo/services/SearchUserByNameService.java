package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchUserByNameService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> searchUserByName(@NonNull String name) {
        return userRepository.findByName(name).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }
}
