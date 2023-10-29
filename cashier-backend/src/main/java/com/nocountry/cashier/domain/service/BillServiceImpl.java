package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.BillRequestDTO;
import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.response.BillResponseDTO;
import com.nocountry.cashier.domain.strategy.transaction.Bill;
import com.nocountry.cashier.domain.strategy.transaction.TransactionStrategy;
import com.nocountry.cashier.domain.usecase.BillService;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import com.nocountry.cashier.persistance.repository.BillRepository;
import com.nocountry.cashier.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final TransactionStrategy strategy;
    private final Utility utility;
    private final AccountRepository accountRepository;

    @Override
    public BillResponseDTO createBill(BillRequestDTO data) {
        AccountEntity originAccount = accountRepository.findById(data.getOrigin()).orElseThrow(() -> new ResourceNotFoundException("Cuenta origen no encontrada", "id", data.getOrigin()));

        //Bill

//        Bill strategyTransaction = strategy.getTransaction(EnumsTransactions.valueOf(data.getType()));
//        var transaction = strategyTransaction.updateBalance(originAccount, data.getAmount());
//        transaction.setState(EnumsState.DONE);
//        transaction.setType(EnumsTransactions.valueOf(data.getType()))
        return null;
    }

    @Override
    public BillResponseDTO findOneByIdAccount(String id, String idAccount) throws Exception {
        return null;
    }

    @Override
    public Page<BillResponseDTO> findAllByIdAccount(String idAccount, PageableDto pageableDto) throws Exception {
        return null;
    }

    @Override
    public Page<BillResponseDTO> findByState(EnumsState state, String idAccount, PageableDto pageableDto) throws Exception {
        return null;
    }

    @Override
    public Page<BillResponseDTO> findByType(String bill_type, String idAccount, PageableDto pageableDto) throws Exception {
        return null;
    }

    @Override
    public Page<BillResponseDTO> findByAmount(BigDecimal amount, String idAccount, PageableDto pageableDto) throws Exception {
        return null;
    }
    @Override
    public BillResponseDTO create(BillRequestDTO data) {
        return null;
    }

    @Override
    public Page<BillResponseDTO> getAll(PageableDto pageable) {
        return null;
    }

    @Override
    public Optional<BillResponseDTO> getById(String s) {
        return Optional.empty();
    }

    @Override
    public BillResponseDTO update(String s, BillRequestDTO data) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

}
