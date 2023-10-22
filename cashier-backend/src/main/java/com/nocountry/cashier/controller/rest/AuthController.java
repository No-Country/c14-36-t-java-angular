package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthConfirmedDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.controller.dto.response.AuthenticatedUserDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.domain.usecase.AuthService;
import com.nocountry.cashier.domain.usecase.UserService;
import com.nocountry.cashier.domain.usecase.qr.QRGeneratorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final QRGeneratorService qrService;

    @PostMapping("/")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserRequestDTO authRequestDTO) {
        String urlConfirm = ServletUriComponentsBuilder.fromCurrentRequest().path("{path}").buildAndExpand("confirm").toUriString() + "?token=";
        AuthResponseDTO register = authService.register(authRequestDTO, urlConfirm);
        return ResponseEntity.ok().body(Map.of("data", register));
    }

    @GetMapping("/")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody AuthRequestDTO authDto) {
        AuthenticatedUserDTO authenticate = authService.authenticate(authDto);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(authenticate.getId()).toUriString();
        return ResponseEntity.ok().body(Map.of("data", authenticate, "url", uri));
    }


    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirmCustomer(@RequestParam String token) {
        AuthConfirmedDTO confirmed = authService.confirm(token);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{qr}").path("view").buildAndExpand(confirmed.getQr()).toUriString();
        return ResponseEntity.ok().body(Map.of("data", confirmed, "urlQr", uri));
    }

    // ? OJO CON ESTO SE MOVE TODAS LAS FUNCIONES A USER CONTROLLER
    @GetMapping("/{uuid}/view")
    public ResponseEntity<?> findCustomerById(@PathVariable @NotBlank(message = "No puede ser vacío") String uuid) {
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "Usuario Encontrado", userService.getById(uuid).get()));
    }

    @GetMapping("/confirm/{qr}")
    public ResponseEntity<?> showQrCustomer(@PathVariable String qr) {
        return qrService.uploadLocalImage(qr);
    }
}
