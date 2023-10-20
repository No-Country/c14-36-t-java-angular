package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.domain.usecase.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static com.nocountry.cashier.util.Constant.API_VERSION;
import static com.nocountry.cashier.util.Constant.RESOURCE_USER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = API_VERSION + RESOURCE_USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //? a la anotación -> ? se le llama wildcard
    @GetMapping
    public ResponseEntity<?> getAllCustomers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "4") Integer size,
                                             PageableDto pageableDto) {
        pageableDto.setPage(page);
        pageableDto.setSize(size);
        List<UserResponseDTO> content = userService.getAll(pageableDto).getContent();
        Map<String, Object> response = Map.of("message", "Listado de Usuarios", "data", content);
        return new ResponseEntity<>(response, OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO userResponse = userService.create(requestDTO);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(userResponse.id()).toUriString();
        return ResponseEntity.status(CREATED).body(uri);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Map<String, Object>> addCustomerWithPhoto(@RequestPart("file") MultipartFile file,// * al usar @REQUESTPART NO SE PUEDE USAR @Valid
                                                                  @RequestParam("uuid") String uuid) {
        var userResponseDTO = userService.addUserWithImage(uuid, file);
        return new ResponseEntity<>(Map.of("data", userResponseDTO), OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@PathParam(value = "uuid") String uuid) {
        userService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody UserRequestDTO userRequestDTO,
                                            @PathVariable @NotBlank(message = "No puede ser vacío") String uuid) {
        UserResponseDTO update = userService.update(uuid, userRequestDTO);
        return ResponseEntity.ok(new GenericResponseDTO<>(true,"actualizado correctamente",update));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getCustomerById(@PathVariable @NotBlank(message = "No puede ser vacío") String uuid){
        return ResponseEntity.ok(new GenericResponseDTO<>(true,"Usuario Encontrado",userService.getById(uuid).get()));
    }

}
