package com.nocountry.cashier.controller.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AccountResponseDTO {

    private String idAccount;

    private String cvu;

    private LocalDate openAccountDate;

    private Double TotalAccount;

    private boolean status;
}
