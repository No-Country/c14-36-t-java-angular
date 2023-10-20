package com.nocountry.cashier.domain.usecase;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
public interface AuthService{
    AuthResponseDTO register(UserRequestDTO userRequestDTO, String url);
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    /*default Map<String, Object> verifyAccount(String otp){
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
    }*/
    Map<String, Object> confirm(String token);
}
