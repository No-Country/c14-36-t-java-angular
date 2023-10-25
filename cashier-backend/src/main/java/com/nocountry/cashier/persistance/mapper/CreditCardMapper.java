package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.persistance.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreditCardMapper {

    CreditCardResponseDTO togetCardDTO(CreditCardEntity creditCardEntity);

    List<CreditCardResponseDTO> toGetListCardDTO(List<CreditCardEntity> creditCardEntityList);
}
