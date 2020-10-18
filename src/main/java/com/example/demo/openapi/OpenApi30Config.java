package com.example.demo.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {

    @Bean
    public OpenAPI customOpenAPI() {
        final var securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(info())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(components(securitySchemeName));
    }

    private Info info() {
        return new Info().title("Demo Spring Boot API")
                .description("# Demo Spring Boot description")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact().name("David").email("dperezcabrera@gmail.com"));
    }

    private Components components(String securitySchemeName) {
        return new Components().addSecuritySchemes(securitySchemeName,
                new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));
    }
}
