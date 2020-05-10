package com.example.demo.user.services;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.mappers.UserMapper;
import com.example.demo.user.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchUsersByNameService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> searchUsersByName(@NonNull String name) {
        return userRepository.findByName(name).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }
}
