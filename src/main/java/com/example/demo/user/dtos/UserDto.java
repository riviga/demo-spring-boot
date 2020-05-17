package com.example.demo.user.dtos;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserDto {

    private Long id;

    @NotNull
    private String name;
}
