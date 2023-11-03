package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.strategy.transaction.Transaction;
import com.nocountry.cashier.domain.strategy.transaction.TransactionStrategy;
import com.nocountry.cashier.domain.usecase.TransactionService;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import com.nocountry.cashier.persistance.mapper.TransactionMapper;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import com.nocountry.cashier.persistance.repository.TransactionRepository;
import com.nocountry.cashier.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final TransactionStrategy strategy;
    private final Utility utility;
    private final AccountRepository accountRepository;


    @Transactional
    @Modifying
    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO data) {

        AccountEntity originAccount = accountRepository.findById(data.getOrigin()).orElseThrow(() -> new ResourceNotFoundException("Cuenta origen no encontrada", "id", data.getOrigin()));
        AccountEntity destinyAccount = accountRepository.findById(data.getDestination()).orElseThrow(() -> new ResourceNotFoundException("Cuenta Destino no encontrada", "id", data.getDestination()));

        Transaction strategyTransaction = strategy.getTransaction(EnumsTransactions.valueOf(data.getType()));
        var transaction = strategyTransaction.updateBalance(originAccount, destinyAccount, data.getAmount());
        transaction.setState(EnumsState.DONE);
        transaction.setType(EnumsTransactions.valueOf(data.getType()));
        transaction.setReason(data.getReason());
        TransactionEntity saveTrasaction = transactionRepository.save(transaction);
        return mapper.toTransactionResponseDto(saveTrasaction);
    }


    @Override
    public TransactionResponseDTO create(TransactionRequestDTO data) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> getAll(PageableDto pageableDto) {
        Pageable pageable = utility.setPageable(pageableDto);
        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);

        List<TransactionResponseDTO> responseDtoList = transactions.getContent().
                stream().
                map(mapper::toTransactionResponseDto).
                toList();
        return new PageImpl<>(responseDtoList);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionResponseDTO> getById(String s) {
        Function<String, Optional<TransactionEntity>> function = transactionRepository::findById;
        TransactionEntity transactionEntity = function.apply(s).orElseThrow(() -> new ResourceNotFoundException("Transaccion no encontrada", "id", s));
        return Optional.of(mapper.toTransactionResponseDto(transactionEntity));
    }

    @Override
    public TransactionResponseDTO update(String s, TransactionRequestDTO data) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponseDTO findOneByIdAccount(String id, String idAccount) {

        TransactionEntity transactionEntity = transactionRepository.findOneByIdAccount(id, idAccount);
        if (transactionEntity == null) {
            throw new ResourceNotFoundException("Transaccion no encontrada", "id", id);
        }
        return mapper.toTransactionResponseDto(transactionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findAllByIdAccount(String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<TransactionEntity> transactionPage = transactionRepository.findAllByIdAccount(idAccount, pageable);

            // Mapear la lista de entidades a DTOs
            List<TransactionResponseDTO> responseDtoList = transactionPage.getContent().stream()
                    .map(mapper::toTransactionResponseDto)
                    .toList();

            return new PageImpl<>(responseDtoList, pageable, transactionPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findByState(EnumsState state, String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<TransactionEntity> transactionPage = transactionRepository.findByState(state, idAccount, pageable);
            // Mapear la lista de entidades a DTOs
            List<TransactionResponseDTO> responseDtoList = transactionPage.getContent().stream()
                    .map(mapper::toTransactionResponseDto)
                    .toList();
            return new PageImpl<>(responseDtoList, pageable, transactionPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findByType(EnumsTransactions type, String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<TransactionEntity> transactionPage = transactionRepository.findByType(type, idAccount, pageable);
            // Mapear la lista de entidades a DTOs
            List<TransactionResponseDTO> responseDtoList = transactionPage.getContent().stream()
                    .map(mapper::toTransactionResponseDto)
                    .toList();
            return new PageImpl<>(responseDtoList, pageable, transactionPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findByAmount(BigDecimal amount, String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<TransactionEntity> transactionPage = transactionRepository.findByAmount(amount, idAccount, pageable);
            // Mapear la lista de entidades a DTOs
            List<TransactionResponseDTO> responseDtoList = transactionPage.getContent().stream()
                    .map(mapper::toTransactionResponseDto)
                    .toList();
            return new PageImpl<>(responseDtoList, pageable, transactionPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
