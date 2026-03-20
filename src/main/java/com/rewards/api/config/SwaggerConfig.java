package com.rewards.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI rewardsOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Customer Rewards API")
                        .description("Spring Boot REST API to calculate reward points")
                        .version("1.0"));
    }
}
