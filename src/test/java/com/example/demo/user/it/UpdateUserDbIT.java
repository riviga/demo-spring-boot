package com.example.demo.user.it;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.entities.User;
import com.example.demo.user.mappers.UserMapperImpl;
import com.example.demo.user.repositories.UserRepository;
import com.example.demo.user.services.UpdateUserService;
import com.example.demo.user.UserMother;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("service-db")
@DataJpaTest(showSql = false, properties = {"spring.datasource.url=jdbc:h2:mem:testdb"})
@Import({
    UpdateUserService.class,
    UserMapperImpl.class
})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateUserDbIT {

    private final UserRepository userRepository;
    private final UpdateUserService updateUserService;

    private UserMother userMother = new UserMother();

    @Test
    public void testChangeNameUserHappyCase() {
        // Given
        User defaultNameUser = userMother.defaultNameUser();
        UserDto john = userMother.johnDto();
        User expectedUser = userMother.john();
        userRepository.save(defaultNameUser);

        // When
        updateUserService.updateUser(john);
        User result = userRepository.getOne(john.getId());

        // Then
        assertEquals(expectedUser, result);
    }
}
