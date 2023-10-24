package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface AccountMapper {

    AccountResponseDTO toGetAccountDTO(AccountEntity accountEntity);

    AccountEntity toGetAccountEntity(AccountResponseDTO accountResponseDTO);
    List<AccountResponseDTO> toGetAccountDTOList(List<AccountEntity> accountEntityList);
}
