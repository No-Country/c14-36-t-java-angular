package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.response
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@JsonPropertyOrder({"id","name", "lastName","image", "address", "dni", "phone","idAccount","idCard", "email"})
@JsonRootName(value = "data")
public record UserResponseDTO(
        String id,
        String name,
        String lastName,
        ImageResponseDTO image,
        String address,
        String dni,
        String phone,
        String email,
        LocalDate birthDate,
        LocalDate openAccountDate,
        String idAccount,
        String idCard,
        LocalDateTime createdDate





) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

