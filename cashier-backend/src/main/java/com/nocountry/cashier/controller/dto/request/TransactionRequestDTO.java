package com.nocountry.cashier.controller.dto.request;

import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TransactionRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDateTime dateEmit;
    private String type;
    private String amount;
    private String origin;
    @NotEmpty(message = "Debe ingresar el destino")
    private String destination;
    private String state;

}
