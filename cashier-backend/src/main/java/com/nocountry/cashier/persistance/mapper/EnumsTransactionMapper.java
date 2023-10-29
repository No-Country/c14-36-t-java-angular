package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.enums.EnumsTransactions;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper()
public interface EnumsTransactionMapper {
    //EnumsTransactionMapper INSTANCE = Mappers.getMapper(EnumsTransactionMapper.class);
    //@EnumMapping(nameTransformationStrategy = "suffix",configuration = "_TYPE")
    @ValueMappings({
            @ValueMapping(target = "TRANSFER", source = "TRANSFER"),
            @ValueMapping(target = "PAYMENT", source = "PAYMENT"),

    })
    EnumsTransactions toEnumsTransactionMapper(EnumsTransactions enumsTransactions);

    //@InheritInverseConfiguration
    //EnumsTransactions toEnumsTransactions(EnumsTransactionMapper toEnumsTransactionMapper);
}
