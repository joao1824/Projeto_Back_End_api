package com.projeto.api.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;


@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class SwaggerConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(new Info()
            .title("Projeto API")
            .version("1.0")
            .description("Documentação da API do projeto de Backend - Requer Java 17 ou superior (testado com Java 17 e Java 22). Utiliza Spring Boot 3.1.4, Spring Security, JWT para autenticação, JPA/Hibernate para persistência de dados, e H2 como banco de dados em memória para desenvolvimento e testes.")
                );
    }


}
