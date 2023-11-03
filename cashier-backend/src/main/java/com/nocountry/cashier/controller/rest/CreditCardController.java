package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.domain.usecase.CreditCardService;
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
@RequestMapping(value = API_VERSION + RESOURCE_CARD)
@Tag(name = "Management Credit Card", description = "Credit Card API")
public class CreditCardController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CreditCardService creditCardService;

    @Operation(
            description = "Get All Cards",
            summary = "Get All Cards",
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
    public ResponseEntity<?> getAllCards(){

        var cardList= creditCardService.getAllCards();

        cardList.forEach(application -> logger.info(cardList.toString()));

        Map<String, Object> response = Map.of("message", "Credit Card List", "data", cardList);

        return new ResponseEntity<>(response, OK);
    }


    @Operation(
            description = "Find Card By Id",
            summary = "SEARCH FOR A CARD BY THEIR ID",
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
    @GetMapping("/{idCard}")
    public ResponseEntity<?> getCard(@PathVariable String idCard){

        CreditCardResponseDTO card = creditCardService.getCard(idCard);

        if(card == null){
            throw new ResourceNotFoundException("Doesn´t Found card id: " +idCard);
        }
        return ResponseEntity.ok(new GenericResponseDTO<>(true, "Credit Card Encontrada",card));
    }

    @Operation(
            description = "Created Credit Card",
            summary = "Created Credit Card",
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
    public ResponseEntity<?> createCard(@RequestParam("uuidUser") String uuidUser){

        CreditCardResponseDTO cardResponseDTO  = creditCardService.createCard(uuidUser);

        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(cardResponseDTO.getIdCard()).toUriString();

        return ResponseEntity.status(CREATED).body(uri);


    }

    @Operation(
            description = "Delete Card By Id",
            summary = "Logical deletion of the card in the database",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    @DeleteMapping("/{idCard}")
    public ResponseEntity<Map<String, Boolean>>
    deleteCard(@PathVariable String idCard){
        CreditCardResponseDTO card = creditCardService.getCard(idCard);

        if(card == null){
            throw new ResourceNotFoundException("ID not found " +idCard);
        }
        creditCardService.deleteCard(idCard);
        //JSON{"delete" : true}
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
