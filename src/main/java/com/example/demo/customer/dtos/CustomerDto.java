package com.example.demo.customer.dtos;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CustomerDto {

    private Long id;

    @NotNull
    private String name;
}
