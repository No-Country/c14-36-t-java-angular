package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.generic.ApiCrudGeneric;
import com.nocountry.cashier.persistance.entity.TransactionEntity;

import java.util.Date;
import java.util.List;

public interface TransactionService extends ApiCrudGeneric<TransactionRequestDTO, TransactionResponseDTO, PageableDto,String> {
  TransactionResponseDTO getTrasactionByTypeTransaction(String typeTran);
  TransactionResponseDTO  getTrasactionByAmount(Long amount);
  TransactionResponseDTO getTrasactionByDate(Date date);
  TransactionResponseDTO getTrasactionByState(String state);


}
