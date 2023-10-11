package com.nocountry.cashier.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nocountry.cashier.persistance.entity.UserEntity;
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
public class UserRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @Pattern(regexp = "^[a-zA-ZÑñ ]+$", message = "No se permite carácteres especiales y números.")
    @NotBlank(message = "Este campo no debe consistir solo en espacios en blanco")
    @NotEmpty(message = "Este campo no puede estar vacío. Ingrese su nombre")
    private String name;

    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "No se permite carácteres especiales y números.")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @NotEmpty(message = "no puede estar vacío. Ingrese sus apellidos")
    private String lastName;

    @NotEmpty(message = "Debe ingresar un número de dni")
    private String dni;
    @Pattern(regexp = "^[0-9]{9}$", message = "El número de celular debe tener 9 dígitos")
    @NotEmpty(message = "Debe ingresar un número de celular")
    private String phone;

    @NotEmpty(message = "es requerido.")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @Pattern(regexp = "^[a-z0-9ñÑ]+(?!.*(?:\\+{2,}|\\-{2,}|\\.{2,}))(?:[\\.+\\-_]{0,1}[a-z0-9Ññ])*@gmail\\.com$", message = "Debe ser un correo tipo gmail.")
    private String email;

    private String address;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$", message = "Ingresa el siguiente formato de fecha dd-MM-yyyy")
    private String birthDate;
}