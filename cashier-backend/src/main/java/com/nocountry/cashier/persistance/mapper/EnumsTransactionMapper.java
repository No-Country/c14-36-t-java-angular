package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.enums.EnumsTransactions;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface EnumsTransactionMapper {
    //EnumsTransactionMapper INSTANCE = Mappers.getMapper(EnumsTransactionMapper.class);
    //@EnumMapping(nameTransformationStrategy = "suffix",configuration = "_TYPE")
    @ValueMappings({
            @ValueMapping(target = "INCOME",source = "INCOME"),
            @ValueMapping(target = "EGRESS",source = "EGRESS"),
            @ValueMapping(target = "TRANSFER",source = "TRANSFER"),
            @ValueMapping(target = "DEPOSIT",source = "DEPOSIT"),
            @ValueMapping(target = "PAYMENT_QR",source = "PAYMENT_QR"),

    })
    EnumsTransactions toEnumsTransactionMapper(EnumsTransactions enumsTransactions);

    //@InheritInverseConfiguration
    //EnumsTransactions toEnumsTransactions(EnumsTransactionMapper toEnumsTransactionMapper);
}
