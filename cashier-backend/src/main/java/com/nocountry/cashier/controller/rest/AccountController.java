package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.domain.usecase.AccountService;

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
@RequestMapping(value = API_VERSION + RESOURCE_ACCOUNT)
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccount(){

        var accountList= accountService.getAllAccounts();

        accountList.forEach(application -> logger.info(accountList.toString()));

        Map<String, Object> response = Map.of("message", "Accounts List", "data", accountList);

        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<?> getAccount(@PathVariable String idAccount){

        AccountResponseDTO account = accountService.getAccount(idAccount);

        if(account == null){
            throw new ResourceNotFoundException("Doesn´t Found account id: " +idAccount);
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestParam("uuidUser") String uuidUser){

        AccountResponseDTO accountResponseDTO = accountService.createAccount(uuidUser);

        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(accountResponseDTO.getIdAccount()).toUriString();

        return ResponseEntity.status(CREATED).body(uri);
    }

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
