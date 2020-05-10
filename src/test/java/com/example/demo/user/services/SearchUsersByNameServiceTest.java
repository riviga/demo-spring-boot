package com.example.demo.user.services;

import com.example.demo.user.UserMother;
import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;
import com.example.demo.user.mappers.UserMapper;
import com.example.demo.user.repositories.UserRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Tag("unit-test")
@ExtendWith(MockitoExtension.class)
public class SearchUsersByNameServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private UserMother userMother = new UserMother();

    private SearchUsersByNameService instance;

    @BeforeEach
    void initEachTest() {
        instance = new SearchUsersByNameService(userRepository, userMapper);
    }

    @Test
    public void testSearchUsersByNameHappyCase() {
        // Given
        String name = "John";
        List<User> users = Arrays.asList(userMother.john(), userMother.john2(), userMother.john3());
        List<UserDto> expectedUsers = Arrays.asList(userMother.johnDto(), userMother.john2Dto(), userMother.john3Dto());
        given(userRepository.findByName(name)).willReturn(users);

        // When
        List<UserDto> result = instance.searchUsersByName(name);

        // Then
        assertEquals(expectedUsers, result);
    }

    @Test
    public void testSearchUsersByNameOneHappyCase() {
        // Given
        String name = "John";
        List<User> users = Arrays.asList(userMother.john());
        List<UserDto> expectedUsers = Arrays.asList(userMother.johnDto());
        given(userRepository.findByName(name)).willReturn(users);

        // When
        List<UserDto> result = instance.searchUsersByName(name);

        // Then
        assertEquals(expectedUsers, result);
    }

    @Test
    public void testSearchUsersByNameEmptyHappyCase() {
        // Given
        String name = "John";
        List<User> users = Collections.emptyList();
        List<UserDto> expectedUsers = Collections.emptyList();
        given(userRepository.findByName(name)).willReturn(users);

        // When
        List<UserDto> result = instance.searchUsersByName(name);

        // Then
        assertEquals(expectedUsers, result);
    }

    @Test
    public void testSearchUsersByNameNullPointerException() {
        // Given
        String name = null;

        // When
        assertThrows(NullPointerException.class, () -> instance.searchUsersByName(name));

        // Then
        then(userRepository).shouldHaveNoInteractions();
    }
}
