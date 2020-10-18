package com.example.demo.customer.it;

import com.example.demo.customer.dtos.CustomerDto;
import com.example.demo.customer.CustomerAgent;
import com.example.demo.customer.CustomerMother;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("web-db")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SearchCustomersByNameIT {

    private CustomerMother customerMother = new CustomerMother();

    private final CustomerAgent customerAgent;

    @Test
    public void testSearchCustomersByNameHappyCase() throws Exception {
        // Given
        var name = "John";
        // customerAgent.registered().authenticated();
        var expectedResult = Arrays.asList(customerMother.johnDto(), customerMother.john2Dto(), customerMother.john3Dto());
        expectedResult.forEach(u -> customerAgent.createCustomer(u));

        // When
        var result = customerAgent.searchCustomersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearchCustomersByNameOneHappyCase() throws Exception {
        // Given
        var name = "Peter";
        // customerAgent.registered().authenticated();
        var expectedResult = Arrays.asList(customerMother.peterDto());
        customerAgent.createCustomer(customerMother.peterDto());

        // When
        var result = customerAgent.searchCustomersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearchCustomersByNameEmptyHappyCase() throws Exception {
        // Given
        var name = "None";
        //customerAgent.registered().authenticated();
        var expectedResult = Collections.emptyList();

        // When
        var result = customerAgent.searchCustomersByName(name);

        // Then
        assertEquals(expectedResult, result);
    }
}
