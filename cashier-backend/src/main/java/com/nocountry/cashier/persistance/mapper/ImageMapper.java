package com.nocountry.cashier.persistance.mapper;

import com.nocountry.cashier.controller.dto.response.ImageResponseDTO;
import com.nocountry.cashier.persistance.entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.persistance.mapper
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {


    @Mapping(target = "id", ignore = true)
    ImageEntity toImageEntity(ImageResponseDTO imageResponseDTO);
    ImageResponseDTO toImageDto(ImageEntity image);

}
