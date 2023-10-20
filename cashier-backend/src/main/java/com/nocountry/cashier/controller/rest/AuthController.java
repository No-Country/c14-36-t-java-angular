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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Locale;
import java.util.Map;

import static com.nocountry.cashier.util.Constant.*;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.rest
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION )
public class AuthController {
    private final AuthService authService;
    @PostMapping(RESOURCE_REGISTER+"/")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserRequestDTO authRequestDTO) {
        String url = ServletUriComponentsBuilder.fromCurrentRequest().path("{path}").buildAndExpand("confirm").toUriString()+"?token=";
        AuthResponseDTO register = authService.register(authRequestDTO,url);
        return ResponseEntity.ok().body(Map.of("data",register));
    }

    @PostMapping(RESOURCE_AUTH+"/")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody AuthRequestDTO authDto){
        AuthResponseDTO authenticate = authService.authenticate(authDto);
        return ResponseEntity.ok().body(authenticate);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirm(@RequestParam String token) {
        return ResponseEntity.ok(authService.confirm(token));
    }
}
