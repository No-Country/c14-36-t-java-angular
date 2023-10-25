package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses ={
        EnumsTransactionMapper.class,
        EnumsStateMapper.class,
        AccountMapper.class
})
public interface TransactionMapper {
//    @Mappings({
//            @Mapping(target ="id" ,source ="id"),
//            @Mapping(target = "dateEmit",source = "dateEmit"),
//            @Mapping(target = "type",source = "type"),
//            @Mapping(target = "amount",source = "amount"),
//            @Mapping(target = "origin",source = "origin"),
//            @Mapping(target = "destination",source = "destination"),
//            @Mapping(target = "state",source = "state"),
//            @Mapping(target = "AccounEntyti.id_account",source = "AccounEntyti.id_Account")
//
//    })
    TransactionEntity toTransactionEntity(TransactionRequestDTO transactionRequestDTO);
    @InheritInverseConfiguration
    TransactionResponseDTO toTransactionResponseDto(TransactionEntity transactionEntity);

    List<TransactionRequestDTO> toTransactionRequestDtoList (List<TransactionEntity> transactionEntityList);
    List<TransactionEntity> toTransactionEntityList(List<TransactionRequestDTO> transactiRequestDTOList);

    /*
    * GetProduct producToGetDTO(Product product);
    @InheritInverseConfiguration
    Product toEnity(GetProduct getProduct);
    List<GetProduct> toGetProductList(List<Product> productList);
    List<Product> toEntityList(List<GetProduct> getProductList);
*/
}
