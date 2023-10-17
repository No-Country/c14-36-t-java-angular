package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.domain.usecase.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.nocountry.cashier.util.Constant.API_VERSION;
import static com.nocountry.cashier.util.Constant.RESOURCE_AUTH;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.rest
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION + RESOURCE_AUTH)
public class AuthController {
    private final AuthService authService;
    @PostMapping("/")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserRequestDTO authRequestDTO) {
        AuthResponseDTO register = authService.register(authRequestDTO);
        return ResponseEntity.ok().body(Map.of("data",register));
    }

    @GetMapping("/")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody AuthRequestDTO authDto,HttpServletRequest request){
        String token = request.getHeader("Authorization");
        AuthResponseDTO authenticate = authService.authenticate(authDto, token);
        return ResponseEntity.ok().body(authenticate);
    }
}
