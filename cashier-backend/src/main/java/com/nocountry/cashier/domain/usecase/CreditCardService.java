package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.persistance.entity.CreditCardEntity;

import java.util.List;

public interface CreditCardService {

    List<CreditCardResponseDTO> getAllCards();

    CreditCardResponseDTO getCard(String idCard);
    public CreditCardEntity createCard(String uuidUser);
}
