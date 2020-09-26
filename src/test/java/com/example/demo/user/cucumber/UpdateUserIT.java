package com.example.demo.user.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

@Tag("acceptance")
@CucumberOptions(
        features = "test/features/users/update-user/update-user.feature",
        plugin = {"pretty", "html:target/cucumber/users/update-user.html"})
@RunWith(Cucumber.class)
public class UpdateUserIT {

}
