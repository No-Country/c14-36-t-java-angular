package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.request.BillRequestDTO;
import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.response.BillResponseDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.generic.ApiCrudGeneric;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface BillService extends ApiCrudGeneric<BillRequestDTO, BillResponseDTO, PageableDto,String> {

    BillResponseDTO createBill(BillRequestDTO data);

    BillResponseDTO findOneByIdAccount(String id,String idAccount)throws Exception;

    Page<BillResponseDTO> findAllByIdAccount(String idAccount, PageableDto pageableDto) throws Exception;
    Page<BillResponseDTO> findByState(EnumsState state, String idAccount, PageableDto pageableDto) throws Exception;

    Page<BillResponseDTO> findByType(String bill_type, String idAccount, PageableDto pageableDto) throws Exception;

    Page<BillResponseDTO> findByAmount(BigDecimal amount, String idAccount, PageableDto pageableDto) throws Exception;

}
