package com.nocountry.cashier.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.request
 * @license Lrpa, zephyr cygnus
 * @since 30/10/2023
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^[a-zA-ZÑñ ]+$", message = "No se permite carácteres especiales y números.")
    private String name;

    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "No se permite carácteres especiales y números.")
    private String lastName;

    @Email(message = "Debe ser un correo electronico.")
    private String email;

    private String dni;

    @Pattern(regexp = "^[0-9]{6,12}$", message = "El número de celular debe tener hasta 12 dígitos")
    private String phone;

    @Pattern(regexp = "^([a-zA-Z0-9,ñ]){8,20}$",
            message = "Password must contain at least 8 characters including letters, numbers, spaces and commas")
    private String password;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Ingresa el siguiente formato de fecha yyyy-MM-dd")
    private String birthDate;
}
