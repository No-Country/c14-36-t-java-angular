package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.*;
import com.nocountry.cashier.domain.usecase.AuthService;
import com.nocountry.cashier.domain.usecase.UserService;
import com.nocountry.cashier.domain.usecase.qr.QRGeneratorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

import static com.nocountry.cashier.util.Constant.API_VERSION;
import static com.nocountry.cashier.util.Constant.RESOURCE_REGISTER;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.rest
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_VERSION+RESOURCE_REGISTER)
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final QRGeneratorService qrService;


    @PostMapping( "/")
    public ResponseEntity<Map<String, AuthResponseDTO>> registerCustomer(@Valid @RequestBody UserRequestDTO authRequestDTO) {
        String urlConfirm = ServletUriComponentsBuilder.fromCurrentRequest().path("{path}").buildAndExpand("confirm").toUriString() + "?token=";
        AuthResponseDTO register = authService.register(authRequestDTO, urlConfirm);
        return ResponseEntity.ok().body(Map.of("data", register));
    }

    @PostMapping("/auth/")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody AuthRequestDTO authDto) {
        AuthenticatedUserDTO authenticate = authService.authenticate(authDto);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(authenticate.getId()).toUriString();
        return ResponseEntity.ok().body(Map.of("data", authenticate, "url", uri));
    }


    @GetMapping(path =  "/confirm")
    public ResponseEntity<?> confirmCustomer(@RequestParam String token) {
        AuthConfirmedDTO confirmed = authService.confirm(token);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{qr}").path("/view").buildAndExpand(confirmed.getQr()).toUriString();
        return ResponseEntity.ok().body(Map.of("data", confirmed, "urlQr", uri));
    }

    // ? OJO CON ESTO SE MOVE TODAS LAS FUNCIONES A USER CONTROLLER
    @GetMapping("/{uuid}/view")
    public ResponseEntity<GenericResponseDTO<UserResponseDTO>> findCustomerById(@PathVariable @NotBlank(message = "No puede ser vacío") String uuid) {
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "Usuario Encontrado", userService.getById(uuid).get()));
    }

    @GetMapping("/confirm/{qr}/view")
    public ResponseEntity<Resource> showQrCustomer(@PathVariable String qr) {
        return qrService.uploadLocalImage(qr);
    }


}