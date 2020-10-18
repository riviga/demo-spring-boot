package com.example.demo.customer.it;

import com.example.demo.customer.dtos.CustomerDto;
import com.example.demo.customer.entities.Customer;
import com.example.demo.customer.mappers.CustomerMapperImpl;
import com.example.demo.customer.services.UpdateCustomerService;
import com.example.demo.customer.CustomerMother;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.customer.repositories.CustomerRepository;

@Tag("service-db")
@DataJpaTest(showSql = false, properties = {"spring.datasource.url=jdbc:h2:mem:testdb"}, useDefaultFilters = false)
@Import({
    UpdateCustomerService.class,
    CustomerMapperImpl.class
})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateCustomerDbIT {

    private final CustomerRepository customerRepository;
    private final UpdateCustomerService updateCustomerService;

    private CustomerMother customerMother = new CustomerMother();

    @Test
    public void testChangeNameCustomerHappyCase() {
        // Given
        var defaultNameCustomer = customerMother.defaultNameCustomer();
        var john = customerMother.johnDto();
        var expectedCustomer = customerMother.john();
        customerRepository.save(defaultNameCustomer);

        // When
        updateCustomerService.updateCustomer(john);
        var result = customerRepository.getOne(john.getId());

        // Then
        assertEquals(expectedCustomer, result);
    }
}
