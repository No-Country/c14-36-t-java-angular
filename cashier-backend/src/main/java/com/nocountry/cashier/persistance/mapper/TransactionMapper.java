package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        EnumsTransactionMapper.class,
        EnumsStateMapper.class,
        AccountMapper.class
})
public interface TransactionMapper {
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "dateEmit", ignore = true)
    @Mapping(target = "accountEntity", ignore = true)
    TransactionEntity toTransactionEntity(TransactionRequestDTO transactionRequestDTO);

    @Mapping(target = "type", expression = "java(transactionEntity.getType().name())")
    @Mapping(target = "state", expression = "java(transactionEntity.getState().name())")
    @Mapping(target= "amount", source = "amount")
    TransactionResponseDTO toTransactionResponseDto(TransactionEntity transactionEntity);

    List<TransactionRequestDTO> toTransactionRequestDtoList(List<TransactionEntity> transactionEntityList);

    List<TransactionEntity> toTransactionEntityList(List<TransactionRequestDTO> transactiRequestDTOList);

}
