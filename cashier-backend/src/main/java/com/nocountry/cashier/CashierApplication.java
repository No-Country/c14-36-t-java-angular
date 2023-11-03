package com.nocountry.cashier;

import com.nocountry.cashier.configuration.FirebaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(FirebaseProperties.class)
@EnableJpaAuditing
public class CashierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashierApplication.class, args);
    }
}
