package com.nocountry.cashier.domain.usecase;


import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.domain.generic.ApiCrudGeneric;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends ApiCrudGeneric<UserRequestDTO, UserResponseDTO, PageableDto, String> {
    UserResponseDTO getClienteByEmail(String email);

    UserResponseDTO getClienteByDni(String dni);

    UserResponseDTO addUserWithImage(String userRequestDTO, MultipartFile file);
}
