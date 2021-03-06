package com.example.demo.customer.mappers;

import com.example.demo.customer.dtos.CustomerDto;
import com.example.demo.customer.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto map(Customer customer);

    @Mapping(target = "id", ignore = true)
    Customer map(String name);

    @Mapping(target = "id", ignore = true)
    Customer map(CustomerDto customerDto, @MappingTarget Customer customer);
}
