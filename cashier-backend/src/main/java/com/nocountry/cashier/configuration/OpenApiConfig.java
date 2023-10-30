package com.nocountry.cashier.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.configuration
 * @license Lrpa, zephyr cygnus
 * @since 29/10/2023
 */

@Configuration
@OpenAPIDefinition(
        servers = @Server(
                url = "http://localhost:8080", //http://181.15.143.132:9698
                description = "production server")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Enter JWT Bearer token",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cashier API")
                        .version("1.0")
                        .description("Documentation Cashier API v1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("CASHIER")
                                .url("https://653dabf9a2ba8c19a0d61ebf--cashier-app-23.netlify.app/"))
                );

    }
    /*
    .contact(new Contact()
                                .name("Luis Peche")
                                .url("https://www.linkedin.com/in/luis-peche/"))
                        .contact(new Contact()
                                .name("Raúl Gómez")
                                .url("https://www.linkedin.com/in/raúl-gómez-44a342252"))
                        .contact(new Contact()
                                .name("Alexander Machicado")
                                .url("https://www.linkedin.com/in/machicadogomezalexander/"))
                        .contact(new Contact()
                                .name("Antonio Bereziuk")
                                .url("https://www.linkedin.com/in/antonioluduenabereziuk/"))
                        .contact(new Contact()
                                .name("Exequiel Baez")
                                .url("https://www.linkedin.com/in/exequiel-baez-156752238/"))
     */
}
