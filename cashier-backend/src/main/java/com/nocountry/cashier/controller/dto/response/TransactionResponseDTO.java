package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"id","dateEmit","typeTrans","origin","destination","state"})
@JsonRootName(value ="data")
public record TransactionResponseDTO(
        String id,
        LocalDateTime dateEmit,
        String type,

        Long amount,
        String origin,
        String destination,
        String state
        ) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
