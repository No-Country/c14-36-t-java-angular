package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.enums.EnumsState;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnumsStateMapper {

    @ValueMappings({
            @ValueMapping(target = "EARRING",source = "EARRING"),
            @ValueMapping(target = "DONE",source = "DONE"),
            @ValueMapping(target = "REJECTED",source = "REJECTED"),


    })

    EnumsState toEnumsStateMapper(EnumsState enumsState);


}
