package com.nocountry.cashier.controller.dto.request;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class TransactionRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Debe Ingresar El Tipo De Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @Schema(description = "Tipo de Transaccion", example = "TRANSFER,PAYMENT",defaultValue = "TRANSFER")
    private String type;


    @Min(value = 0, message = "El monto debe ser mayor a 0")
    @Schema(description = "Monto a transferir", example = "150.5")
    private BigDecimal amount;

    @NotEmpty(message = "Debe Ingresar El Origen De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @Schema(description = "Id de la cuenta emisor", example = "550e8400-e29b-41d4-a716-446655440000")
    private String origin; //id del emisor

    @NotEmpty(message = "Debe Ingresar El Destino De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @Schema(description = "Id de la cuenta Receptora", example = "4789652-e29b-41d4-a716-446655440000")
    private String destination; //id del receptor

    @Pattern(regexp = "^[a-zA-ZÑñ ]+$", message = "No se permite carácteres especiales y números.")
    @Schema(description = "Tipo de transferencia", example = "HONORARIOS, VARIOS, ETC")
    private String reason;

}
