package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.domain.usecase.AccountService;

import com.nocountry.cashier.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


import static com.nocountry.cashier.util.Constant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:4200","*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping(value = API_VERSION + RESOURCE_ACCOUNT)
@Tag(name = "Management Account", description = "Account API")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Operation(
            description = "Get All Accounts",
            summary = "Get All Accounts",
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
    public ResponseEntity<?> getAllAccount(){

        var accountList= accountService.getAllAccounts();

        accountList.forEach(application -> logger.info(accountList.toString()));

        Map<String, Object> response = Map.of("message", "Accounts List", "data", accountList);

        return new ResponseEntity<>(response, OK);
    }


    @Operation(
            description = "Find Account By Id",
            summary = "SEARCH FOR AN ACCOUNT BY THEIR ID",
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
    @GetMapping("/{idAccount}")
    public ResponseEntity<?> getAccount(@PathVariable String idAccount){

        AccountResponseDTO account = accountService.getAccount(idAccount);

        if(account == null){
            throw new ResourceNotFoundException("Doesn´t Found account id: " +idAccount);
        }
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "Cuenta Encontrada",account));
    }


    @Operation(
            description = "Created Account",
            summary = "Created Account",
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
            }
    )
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestParam("uuidUser") String uuidUser){

        AccountResponseDTO accountResponseDTO = accountService.createAccount(uuidUser);

        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(accountResponseDTO.getIdAccount()).toUriString();

        return ResponseEntity.status(CREATED).body(uri);
    }

    @Operation(
            description = "Delete Account By Id",
            summary = "Logical deletion of the account in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Map<String, Boolean>>
    deleteAccount(@PathVariable String idAccount){
        AccountResponseDTO account = accountService.getAccount(idAccount);

        if(account == null){
            throw new ResourceNotFoundException("ID not found " +idAccount);
        }
        accountService.deleteAccount(idAccount);
        //JSON{"delete" : true}
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
