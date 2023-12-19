package com.springproject.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI OpenAPI(){

        return new OpenAPI().info(
                new Info().title("Cloud Vendor")
                        .description("This is Cloud Vendor store API developed by cloudvendor")
                        .version("1.0")
                        .contact(new Contact().name("Enter your Name").email("Enter your email"))
                        .license(new License().name("Apache License"))

        ).externalDocs(new ExternalDocumentation().url("enter your url").description("TEnter your description"));
    }
}
