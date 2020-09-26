package com.example.demo.user.e2e;

import com.example.demo.e2e.WebDriverFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

@Tag("scenario-base")
public class ViewOpenApiE2ET {

    @Test
    void testHappyCase() throws Exception {
        WebDriverFactory factory = new WebDriverFactory();
        WebDriver webDriver = factory.createChromeUser("admin-selenium");
        VirtualUser admin = new VirtualUser("admin", "admin", webDriver);
        
        admin.goToDemo("/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#");
        admin.waitSeconds(30);
    }
}
