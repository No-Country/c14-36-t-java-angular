package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "origin", "destination", "amount","dateEmit", "type", "state"})
@JsonRootName(value = "data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private LocalDateTime dateEmit;
    private String type;
    private BigDecimal amount;
    private String origin;
    private String destination;
    private String state;
    private String reason;
}
