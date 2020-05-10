package com.example.demo.user;

import com.example.demo.user.dtos.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Component
@RequiredArgsConstructor
public class UserAgent {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public ResultActions updateUser(@NonNull UserDto user) {
        return Try.of(() -> mockMvc.perform(put("/users/{userId}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))).get();
    }

    public ResultActions createUser(@NonNull UserDto user) {
        return Try.of(() -> mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))).get();
    }

    public UserDto getUser(@NonNull Long userId) {
        return Try.of(() -> {
            String bodyResponse = mockMvc.perform(get("/users/{userId}", userId))
                    .andReturn().getResponse().getContentAsString();
            return objectMapper.readValue(bodyResponse, UserDto.class);
        }).get();
    }

    public List<UserDto> searchUsersByName(@NonNull String name) throws Exception {
        String bodyResponse = mockMvc.perform(get("/users/search?name={named}", name))
                .andReturn().getResponse().getContentAsString();
        return Arrays.asList(objectMapper.readValue(bodyResponse, UserDto[].class));
    }
}
