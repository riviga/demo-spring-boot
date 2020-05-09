package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Tag("unit-test")
@ExtendWith(MockitoExtension.class)
public class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private UserMother userMother = new UserMother();

    private UpdateUserService instance;

    @BeforeEach
    void initEachTest() {
        instance = new UpdateUserService(userRepository, userMapper);
    }

    @Test
    public void testChangeNameUserHappyCase() {
        // Given
        User defaultNameUser = userMother.defaultNameUser();
        UserDto john = userMother.johnDto();
        User expectedUser = userMother.john();
        given(userRepository.getOne(defaultNameUser.getId())).willReturn(defaultNameUser);

        // When
        instance.updateUser(john);

        // Then
        then(userRepository).should().save(eq(expectedUser));
    }

    @Test
    public void testUpdateUserNotFoundException() {
        // Given
        UserDto john = userMother.johnDto();
        EntityNotFoundException expectedException = new EntityNotFoundException();
        given(userRepository.getOne(john.getId())).willThrow(expectedException);

        // When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> instance.updateUser(john));

        // Then
        then(userRepository).should(times(0)).save(any());
        assertEquals(expectedException, exception);
    }

    @Test
    public void testUpdateUserNullPointerException() {
        // Given
        UserDto nullUser = null;

        // When
        assertThrows(NullPointerException.class, () -> instance.updateUser(nullUser));

        // Then
        then(userRepository).should(times(0)).save(any());
    }

    @Test
    public void testUpdateUserIdNullPointerException() {
        // Given
        String expectedErrorMessage = "El identificador no puede ser nulo";
        UserDto userWithNoId = userMother.userWithNoId();

        // When
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> instance.updateUser(userWithNoId));

        // Then
        System.out.println(ex.getMessage());
        then(userRepository).should(times(0)).save(any());
        assertEquals(expectedErrorMessage, ex.getMessage());
    }

    @Test
    public void testUpdateUserNameNullPointerException() {
        // Given
        String expectedErrorMessage = "El nombre no puede ser nulo";
        UserDto userWithNoName = userMother.userWithNoName();

        // When
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> instance.updateUser(userWithNoName));

        // Then
        System.out.println(ex.getMessage());
        then(userRepository).should(times(0)).save(any());
        assertEquals(expectedErrorMessage, ex.getMessage());
    }
}
