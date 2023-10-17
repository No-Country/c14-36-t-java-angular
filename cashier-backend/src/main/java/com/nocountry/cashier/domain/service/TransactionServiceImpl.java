package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.usecase.TransactionService;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import com.nocountry.cashier.persistance.mapper.TransactionMapper;
import com.nocountry.cashier.persistance.repository.TransactionRepository;
import com.nocountry.cashier.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final Utility utility;

    @Transactional
    @Override
    public TransactionResponseDTO create(TransactionRequestDTO data){

        TransactionEntity transactionSave = new TransactionEntity();

        data.setDateEmit(LocalDateTime.now());

        transactionSave = Optional.of(data)
                .map(mapper :: toTransactionEntity )
                .map(transactionRepository::save)
                .orElseThrow(()-> new GenericException("Oops no pudismos resolver la transaccion", HttpStatus.BAD_REQUEST));



    return mapper.toTransactionResponseDto(transactionSave);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> getAll(PageableDto pageableDto) {
        Pageable pageable= utility.setPageable(pageableDto);
        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);

        List<TransactionResponseDTO> responseDtoList = transactions.getContent().
                stream().
                map(mapper::toTransactionResponseDto).
                toList();
        return new PageImpl<>(responseDtoList);
    }

    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> getTransactionByType(PageableDto pageableDto,String type){
        Pageable pageable = utility.setPageable(pageableDto);
        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);
        List<TransactionResponseDTO> responseDtoList = transactions.getContent().
                stream().filter(x->x.equals(type.toUpperCase())).
                map(mapper::toTransactionResponseDto).toList();
        return new PageImpl<>(responseDtoList);
    }
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> getTransactionByState(PageableDto pageableDto,String state){
        Pageable pageable = utility.setPageable(pageableDto);
        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);
        List<TransactionResponseDTO> responseDtoList = transactions.getContent().
                stream().filter(x->x.equals(state.toUpperCase())).
                map(mapper::toTransactionResponseDto).toList();
        return new PageImpl<>(responseDtoList);
    }
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> getTransactionByDate(PageableDto pageableDto,Date date){
        Pageable pageable = utility.setPageable(pageableDto);
        Page<TransactionEntity> transactions = transactionRepository.findAll(pageable);
        //Predicate<TransactionEntity> predicate = transactions.forEach(transactionEntity -> transactionEntity.getDateEmit())==date;
        List<TransactionResponseDTO> responseDtoList = transactions.getContent().
                stream().filter(x-> {
                    return x.getDateEmit() == date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                }).
                map(mapper::toTransactionResponseDto).toList();
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
    public TransactionResponseDTO getTrasactionByTypeTransaction(String typeTran) {
        return null;
    }

    @Override
    public TransactionResponseDTO getTrasactionByAmount(Long amount) {
        return null;
    }

    @Override
    public TransactionResponseDTO getTrasactionByDate(Date date) {
        return null;
    }

    @Override
    public TransactionResponseDTO getTrasactionByState(String state) {
        return null;
    }
}
