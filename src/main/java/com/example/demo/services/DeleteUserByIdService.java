package com.example.demo.services;

import com.example.demo.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserByIdService {

    private final UserRepository userRepository;

    @Transactional
    public void deleteUserById(@NonNull Long userId) {
        userRepository.deleteById(userId);
    }
}