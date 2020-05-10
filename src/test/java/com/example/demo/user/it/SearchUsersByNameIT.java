package com.example.demo.user.it;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.UserAgent;
import com.example.demo.user.UserMother;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("web-db")
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SearchUsersByNameIT {

    private final UserAgent userAgent;

    private UserMother userMother = new UserMother();

    @Test
    public void testSearchUsersByNameHappyCase() throws Exception {
        // Given
        String name = "John";
        List<UserDto> expectedResult = Arrays.asList(userMother.johnDto(), userMother.john2Dto(), userMother.john3Dto());
        expectedResult.forEach(u -> userAgent.createUser(u));

        // When
        List<UserDto> result = userAgent.searchUsersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testSearchUsersByNameOneHappyCase() throws Exception {
        // Given
        String name = "Peter";
        List<UserDto> expectedResult = Arrays.asList(userMother.peterDto());
        userAgent.createUser(userMother.peterDto());
        
        // When
        List<UserDto> result = userAgent.searchUsersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testSearchUsersByNameEmptyHappyCase() throws Exception {
        // Given
        String name = "None";
        List<UserDto> expectedResult = Collections.emptyList();

        // When
        List<UserDto> result = userAgent.searchUsersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }
}
