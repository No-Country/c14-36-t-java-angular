package com.nocountry.cashier.controller.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class BillRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Debe Ingresar El Origen De La Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String origin; //id del emisor

    @NotEmpty(message = "Debe Ingresar El Tipo De Transaccion")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String type;

    @NotEmpty(message = "Debe Ingresar El Tipo De Factura")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String bill_type;

    @NotEmpty(message = "Debe Ingresar El Numero De Factura")
    @NotBlank(message = "no debe consistir solo en espacios en blanco")
    private String bill_num;


    @Min(value = 0, message = "El monto debe ser mayor a 0")
    private BigDecimal amount;
}
