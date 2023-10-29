package com.nocountry.cashier.controller.dto.request;


import com.nocountry.cashier.persistance.entity.AccountEntity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
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
    private String type;

    @NotEmpty(message = "Debe Ingresar El Monto De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    @Min(value = 0, message = "El monto debe ser mayor a 0")
    private BigDecimal amount;

    @NotEmpty(message = "Debe Ingresar El Origen De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String origin; //id del emisor

    @NotEmpty(message = "Debe Ingresar El Destino De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String destination; //id del receptor

    @Pattern(regexp = "^[a-zA-ZÑñ ]+$", message = "No se permite carácteres especiales y números.")
    private String reason;

}
