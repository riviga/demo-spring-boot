package com.example.demo.user.cucumber;

import com.example.demo.user.dtos.UserDto;
import com.example.demo.user.UserAgent;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:testdb"})
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateUserSteps {

    private final UserAgent userAgent;

    private UserDto initialUser;

    @Given("un usuario existente con id {long} y nombre {word}")
    public void un_usuario_existente_con_id_y_nombre(long id, String name) throws Exception {
        initialUser = new UserDto(id, name);
        userAgent.createUser(initialUser);
    }

    @When("cambiamos el nombre de este usuario por {word}")
    public void cambiamos_el_nombre_de_este_usuario_por(String newName) throws Exception {
        UserDto userDto = new UserDto(initialUser.getId(), newName);
        userAgent.updateUser(userDto).andExpect(status().isOk());
    }

    @Then("tras consultarlo otra vez, su nombre debe ser {word}")
    public void tras_consultarlo_nuevamente_su_nombre_debe_ser_John(String newName) throws Exception {
        initialUser = userAgent.getUser(initialUser.getId());
        assertEquals(newName, initialUser.getName());
    }
}
