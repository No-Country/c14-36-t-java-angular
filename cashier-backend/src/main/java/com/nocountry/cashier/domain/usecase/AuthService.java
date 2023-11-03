package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthConfirmedDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.controller.dto.response.AuthenticatedUserDTO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
public interface AuthService {
    AuthResponseDTO register(UserRequestDTO userRequestDTO, String url);

    AuthenticatedUserDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthConfirmedDTO confirm(String token);
}
