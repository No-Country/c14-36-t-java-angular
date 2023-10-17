package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.domain.usecase.AccountService;
import com.nocountry.cashier.exception.RegisterNotFound;

import com.nocountry.cashier.persistance.entity.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.opencensus.trace.Status.OK;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountResponseDTO> getAllAccount(){

        List<AccountResponseDTO> accountList= accountService.getAllAccounts();

        accountList.forEach(application -> logger.info(accountList.toString()));

        return accountList;
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<AccountEntity> getAccount(@PathVariable String idAccount){

        AccountEntity account = accountService.getAccount(idAccount);

        if(account == null){
            throw new RegisterNotFound("Doesn´t Found account id: " +idAccount);
        }
        return ResponseEntity.ok(account);
    }

   /* @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account){
        logger.info("Account to create: " +account);
        return accountService.createAccount(account);

    }*/

    @PostMapping
    public AccountResponseDTO createAccount(@RequestParam("uuidUser") String uuidUser){

        //var accountResponseDTO = accountService.createAccount(uuidUser);

        //return new ResponseEntity<>(Map.of("exeq", accountResponseDTO), OK);

        return accountService.createAccount(uuidUser);
    }

    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Map<String, Boolean>>
    deleteAccount(@PathVariable String idAccount){
        AccountEntity account = accountService.getAccount(idAccount);

        if(account == null){
            throw new RegisterNotFound("ID not found " +idAccount);
        }
        accountService.deleteAccount(account);
        //JSON{"delete" : true}
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
