package com.nocountry.cashier.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.configuration
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
@Configuration
public class PasswordBcrypt {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
