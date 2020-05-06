package com.example.demo.services;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

@Tag("acceptance")
@CucumberOptions(
        features = "src/test/resources/features/update-user.feature",
        plugin = {"pretty", "html:target/cucumber/update-user.html"})
@RunWith(Cucumber.class)
public class UpdateUserCucumberIT {

}
