package com.nocountry.cashier.controller.dto.request;

import com.nocountry.cashier.persistance.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
public record AuthRequestDTO(

        @NotEmpty(message = "es requerido.")
        @NotBlank(message = "no debe consistir solo en espacios en blanco")
        //@Pattern(regexp = "^[a-z0-9ñÑ]+(?!.*(?:\\+{2,}|\\-{2,}|\\.{2,}))(?:[\\.+\\-_]{0,1}[a-z0-9Ññ])*@gmail\\.com$", message = "Debe ser un correo tipo gmail.")
        // ([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})
        @Email(message = "Debe ser un correo electronico.")
        String email,

        @NotBlank(message = "Password is required")
        @Pattern(regexp = "^([a-zA-Z0-9,ñ!@#$%^&*()_+=-{}\\[\\]:;\"'<>,.?\\/|]){8,20}$",
                message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
        String password
) implements Serializable {
}
