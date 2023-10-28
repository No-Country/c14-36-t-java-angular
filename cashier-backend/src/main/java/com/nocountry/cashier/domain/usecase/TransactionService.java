package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.generic.ApiCrudGeneric;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface TransactionService extends ApiCrudGeneric<TransactionRequestDTO, TransactionResponseDTO, PageableDto, String> {
    TransactionResponseDTO createTransaction(TransactionRequestDTO data);

    TransactionResponseDTO findOneByIdAccount(String id, String idAccount) throws Exception;

    Page<TransactionResponseDTO> findAllByIdAccount(String idAccount, PageableDto pageableDto) throws Exception;

    Page<TransactionResponseDTO> findByState(EnumsState state, String idAccount, PageableDto pageableDto) throws Exception;

    Page<TransactionResponseDTO> findByType(EnumsTransactions type, String idAccount, PageableDto pageableDto) throws Exception;

    Page<TransactionResponseDTO> findByAmount(BigDecimal amount, String idAccount, PageableDto pageableDto) throws Exception;
}
