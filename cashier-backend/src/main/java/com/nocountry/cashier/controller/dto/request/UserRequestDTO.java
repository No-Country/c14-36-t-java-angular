package com.nocountry.cashier.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nocountry.cashier.persistance.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class
UserRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^[a-zA-ZÑñ ]+$", message = "No se permite carácteres especiales y números.")
    @NotBlank(message = "Este campo no debe consistir solo en espacios en blanco")
    @NotEmpty(message = "Este campo no puede estar vacío. Ingrese su nombre")
    private String name;

    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "No se permite carácteres especiales y números.")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @NotEmpty(message = "no puede estar vacío. Ingrese sus apellidos")
    private String lastName;

    @NotEmpty(message = "es requerido.")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    //@Pattern(regexp = "^[a-z0-9ñÑ]+(?!.*(?:\\+{2,}|\\-{2,}|\\.{2,}))(?:[\\.+\\-_]{0,1}[a-z0-9Ññ])*@gmail\\.com$", message = "Debe ser un correo tipo gmail.")
    @Email(message = "Debe ser un correo electronico.")
    private String email;

    @NotEmpty(message = "Debe ingresar un número de dni")
    private String dni;

    @Pattern(regexp = "^[0-9]{6,12}$", message = "El número de celular debe tener hasta 12 dígitos")
    @NotEmpty(message = "Debe ingresar un número de celular")
    private String phone;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^([a-zA-Z0-9,ñ]){8,20}$",
            message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
    private String password;

    private String address;

   @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
   @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Ingresa el siguiente formato de fecha yyyy-MM-dd")
   private String birthDate;
}