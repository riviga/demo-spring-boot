package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("web-db")
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateUserIT {
    
    private final UserAgent userAgent;

    private UserMother userMother = new UserMother();

    @Test
    void testChangeNameUserHappyCase() throws Exception {
        // Given
        String expectedName = "John";
        UserDto noNameUser = userMother.defaultNameUserDto();
        UserDto john = userMother.johnDto();
        userAgent.createUser(noNameUser);

        // When
        ResultActions result = userAgent.updateUser(john);

        // Then
        result.andExpect(status().isOk());
        UserDto user = userAgent.getUser(noNameUser.getId());
        assertEquals(expectedName, user.getName());
    }

    @Test
    void testUpdateUserNotFoundException() throws Exception {
        // Given
        UserDto notExistUser = userMother.notExistUser();

        // When
        ResultActions result = userAgent.updateUser(notExistUser);

        // Then
        result.andExpect(status().isInternalServerError());
    }
}
