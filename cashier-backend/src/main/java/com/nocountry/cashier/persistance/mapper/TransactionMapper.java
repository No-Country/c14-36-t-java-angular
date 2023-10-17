package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.hibernate.Transaction;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses ={
        EnumsTransactionMapper.class,
        EnumsStateMapper.class
})
public interface TransactionMapper {
    @Mappings({
            @Mapping(target ="id" ,source ="id"),
            @Mapping(target = "dateEmit",source = "dateEmit"),
            @Mapping(target = "type",source = "type"),
            @Mapping(target = "amount",source = "amount"),
            @Mapping(target = "origin",source = "origin"),
            @Mapping(target = "destination",source = "destination"),
            @Mapping(target = "state",source = "state")

    })
    TransactionEntity toTransactionEntity(TransactionRequestDTO transactionRequestDTO);
    @InheritInverseConfiguration
    TransactionResponseDTO toTransactionResponseDto(TransactionEntity transactionEntity);

    List<TransactionRequestDTO> toTransactionRequestDtoList (List<TransactionEntity> transactionEntityList);
    List<TransactionEntity> toGetPTransactionEntityList(List<TransactionRequestDTO> transactionEntitRequestDTOList);


}
