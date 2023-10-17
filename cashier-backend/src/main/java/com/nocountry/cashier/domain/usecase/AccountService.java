package com.nocountry.cashier.domain.usecase;


import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.persistance.entity.AccountEntity;

import java.util.List;

public interface AccountService {

    List<AccountResponseDTO> getAllAccounts();

    AccountEntity getAccount(String idAccount);

    //AccountEntity createAccount(AccountEntity accountEntity);

    AccountResponseDTO createAccount(String uuidUser);
    void deleteAccount(AccountEntity accountEntity);
}
