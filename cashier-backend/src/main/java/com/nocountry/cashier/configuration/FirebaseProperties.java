package com.nocountry.cashier.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.config
 * @license Lrpa, zephyr cygnus
 * @since 11/10/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {
    private String bucketName;
    private String accountKey;
    private String urlImageFirebase;
}
