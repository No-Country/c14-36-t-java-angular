package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.persistance.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ImageMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "qr", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "modifyUser", ignore = true)
    @Mapping(target = "openAccountDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "password", source = "password", qualifiedByName = "encoderPass")
    UserEntity toUserEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDto(UserEntity userEntity);

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(birthDate.strip(), formatter);
    }

    @Named("encoderPass")
    default String encoderPass(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


}