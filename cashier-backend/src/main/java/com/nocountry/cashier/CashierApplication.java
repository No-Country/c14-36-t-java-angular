package com.nocountry.cashier;

import com.nocountry.cashier.configuration.FirebaseProperties;
import com.nocountry.cashier.security.jwt.JwtTokenProvider;
import com.nocountry.cashier.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
