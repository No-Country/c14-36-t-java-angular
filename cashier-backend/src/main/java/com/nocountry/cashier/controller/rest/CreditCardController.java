package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.domain.usecase.CreditCardService;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.nocountry.cashier.util.Constant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = API_VERSION + RESOURCE_CARD)
public class CreditCardController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CreditCardService creditCardService;


    @GetMapping
    public ResponseEntity<?> getAllCards(){

        var cardList= creditCardService.getAllCards();

        cardList.forEach(application -> logger.info(cardList.toString()));

        Map<String, Object> response = Map.of("message", "Credit Card List", "data", cardList);

        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/{idCard}")
    public ResponseEntity<?> getCard(@PathVariable String idCard){

        CreditCardResponseDTO card = creditCardService.getCard(idCard);

        if(card == null){
            throw new ResourceNotFoundException("Doesn´t Found card id: " +idCard);
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestParam("uuidUser") String uuidUser){

        CreditCardResponseDTO cardResponseDTO  = creditCardService.createCard(uuidUser);

        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(cardResponseDTO.getIdCard()).toUriString();

        return ResponseEntity.status(CREATED).body(uri);


    }

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
