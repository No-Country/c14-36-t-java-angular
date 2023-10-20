package com.nocountry.cashier.controller.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AccountResponseDTO {

    private String idAccount;

    private String cvu;

    private LocalDate openAccountDate;

    private BigDecimal totalAccount;

    private boolean status;
}
