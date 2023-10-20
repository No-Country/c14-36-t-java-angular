package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.domain.usecase.CreditCardService;
import com.nocountry.cashier.exception.RegisterNotFound;
import com.nocountry.cashier.persistance.entity.CreditCardEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CreditCardController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CreditCardService creditCardService;


    @GetMapping
    public List<CreditCardResponseDTO> getAllCards(){

        List<CreditCardResponseDTO> cardList= creditCardService.getAllCards();

        cardList.forEach(application -> logger.info(cardList.toString()));

        return cardList;
    }

    @GetMapping("/{idCard}")
    public ResponseEntity<CreditCardResponseDTO> getCard(@PathVariable String idCard){

        CreditCardResponseDTO card = creditCardService.getCard(idCard);

        if(card == null){
            throw new RegisterNotFound("Doesn´t Found card id: " +idCard);
        }
        return ResponseEntity.ok(card);
    }

    @PostMapping
    public CreditCardEntity createCard(@RequestParam("uuidUser") String uuidUser){

        return creditCardService.createCard(uuidUser);


    }
}
