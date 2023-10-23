package com.nocountry.cashier.controller.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreditCardResponseDTO {

    private String idCard;

    private String cardNumber;

    private String cardName;

    private LocalDate expirationDate;

    private String securityCode;
}
