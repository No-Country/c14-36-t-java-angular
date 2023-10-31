package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.response.BillResponseDTO;
import com.nocountry.cashier.persistance.entity.BillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EnumsTransactionMapper.class, EnumsStateMapper.class,AccountMapper.class})
public interface BillMapper {
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "dateEmit", ignore = true)
    @Mapping(target = "accountEntity", ignore = true)
    BillEntity toBillEntity(BillResponseDTO billResponseDTO);

    @Mapping(target = "type",expression = "java(billEntity.getType().name())")
    @Mapping(target = "state",expression = "java(billEntity.getState().name())")
    @Mapping(target = "bill_type",source = "bill_type")
    @Mapping(target = "bill_num",source = "bill_num")
    @Mapping(target = "amount",source = "amount")
    @Mapping(target = "voucher_num",source = "voucher_num")

    BillResponseDTO toBillResponseDto(BillEntity billEntity);

}
