package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.response
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@JsonPropertyOrder({"name", "lastName","createdDate","image"})
@JsonRootName(value = "data")
public record UserResponseDTO(
        String id,
        LocalDateTime createdDate,
        String name,
        String lastName,
        ImageResponseDTO image

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

