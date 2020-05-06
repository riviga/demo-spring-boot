package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ResultActions updateUser(@NonNull UserDto user) throws Exception {
        return mockMvc.perform(put("/users/{userId}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
    }

    public ResultActions createUser(@NonNull UserDto user) throws Exception {
        return mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
    }

    public UserDto getUser(@NonNull Long userId) throws Exception {
        String bodyResponse = mockMvc.perform(get("/users/{userId}", userId))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(bodyResponse, UserDto.class);
    }
}
