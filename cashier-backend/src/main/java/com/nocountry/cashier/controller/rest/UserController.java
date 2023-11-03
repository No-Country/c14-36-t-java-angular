package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.UpdateRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.domain.usecase.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@CrossOrigin(origins = {"http://localhost:4200", "*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping(value = API_VERSION + RESOURCE_USER)
@RequiredArgsConstructor
@Tag(name = "Management User", description = "User API")
public class UserController {

    private final UserService userService;

    //http://localhost:8080/v1/api/customers/login?otp=%s
    @Operation(summary = "Login User",
            description = "DEPRECATED NOT USED"
            , deprecated = true)
    @Hidden
    @GetMapping(path = "/login")
    public ResponseEntity<?> loginRegisteredUser(@RequestParam String otp) {
        return ResponseEntity.ok().body(Map.of("data", "login"));
    }

    @Operation(
            description = "Get All Users",
            summary = "Get All Users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Page.class)) }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getAllCustomers(@Parameter(description = "DTO el cual permite listar usuarios", schema = @Schema(implementation = PageableDto.class)) PageableDto pageableDto) {

        //@RequestParam(value = "page", defaultValue = "0") Integer page,
        //@RequestParam(value = "size", defaultValue = "4") Integer size,
        //pageableDto.setPage(page);
        //pageableDto.setSize(size);
        Page<UserResponseDTO> content = userService.getAll(pageableDto);
        //Map<String, Object> response = Map.of("message", "Listado de Usuarios", "data", content);
        return new ResponseEntity<>(content, OK);
    }

    @GetMapping("search/{ramdom}")
    public ResponseEntity<?> getByShortString(@PathVariable String ramdom,
                                              PageableDto pageableDto){
        //@RequestParam(value = "order", defaultValue = "1") Integer order,
        // @RequestParam(value = "field", defaultValue = "id") String field)


        try {
            // Validar que el parámetro de búsqueda no esté vacío o nulo
            if (ramdom == null || ramdom.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'error':'El parámetro de búsqueda es requerido'}");

            }

            System.out.println(ramdom);

            String randomToLowerCase = ramdom.toLowerCase();

            Page<UserResponseDTO> content = userService.findByShortString(randomToLowerCase,pageableDto);
            Map<String, Object> response = Map.of("message", "Listado de Personas Segun letras ingresadas ", "data", content);
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }

    }

  @Operation(
            description = "Created User",
            summary = "DEPRECATED NOT USED",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = String.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    )
            },
            deprecated = true
    )
    @Hidden
    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO userResponse = userService.create(requestDTO);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(userResponse.id()).toUriString();
        return ResponseEntity.status(CREATED).body(uri);
    }

    @Operation(
            description = "Add User With Photo",
            summary = "Route that allows the user to upload a profile picture",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = AuthResponseDTO.class))}

                    )
            }
    )
    @PostMapping(value = "/upload")
    public ResponseEntity<Map<String, Object>> addCustomerWithPhoto(@RequestPart("file") MultipartFile file,// * al usar @REQUESTPART NO SE PUEDE USAR @Valid
                                                                    @RequestParam("uuid") String uuid) {
        var userResponseDTO = userService.addUserWithImage(uuid, file);
        return new ResponseEntity<>(Map.of("data", userResponseDTO), OK);
    }

    @Operation(
            description = "Delete User By Id",
            summary = "Logical deletion of the user in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@PathParam(value = "uuid") String uuid) {
        userService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            description = "Update User",
            summary = "update a user's optional fields",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GenericResponseDTO.class)) }
                    )
            }
    )
    @PatchMapping("/{uuid}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody UpdateRequestDTO updateRequestDTO,
                                            @PathVariable @NotBlank(message = "No puede ser vacío") String uuid) {
        UserResponseDTO update = userService.customisedUpdate(updateRequestDTO,uuid);
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "actualizado correctamente", update));
    }

    @Operation(
            description = "Find User By Id",
            summary = "SEARCH FOR AN USER BY THEIR ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = GenericResponseDTO.class)) }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getCustomerById(@PathVariable @NotBlank(message = "No puede ser vacío") String uuid) {
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "Usuario Encontrado", userService.getById(uuid).get()));
    }

}
