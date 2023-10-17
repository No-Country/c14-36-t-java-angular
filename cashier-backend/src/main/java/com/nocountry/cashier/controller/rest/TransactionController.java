package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.exception.RegisterNotFound;
import com.nocountry.cashier.domain.usecase.TransactionService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.nocountry.cashier.util.Constant.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = API_VERSION + RESOURCE_USER + RESOURECE_TRANSACTION)
@RequiredArgsConstructor
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public ResponseEntity<?> getAllTransactions(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "4") Integer size,
                                             PageableDto pageableDto) {
        pageableDto.setPage(page);
        pageableDto.setSize(size);
        List<TransactionResponseDTO> content = transactionService.getAll(pageableDto).getContent();
        Map<String, Object> response = Map.of("message", "Listado de Transacciones", "data", content);
        return new ResponseEntity<>(response, OK);

    }
//accountList.forEach(application -> logger.info(accountList.toString()));

    @PostMapping("/")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionRequestDTO requestDTO) {
        TransactionResponseDTO transactionResponse = transactionService.create(requestDTO);
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .path("{id}").buildAndExpand(transactionResponse.id()).toUriString();
        return ResponseEntity.status(CREATED).body(uri);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id){
        return ResponseEntity.ok(new GenericResponseDTO<>(true,"Transaccion Encontrada",transactionService.getById(id).get()));
    }
    @GetMapping("/search/state")
    public ResponseEntity<?> getTransactionsByState(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "4") Integer size, PageableDto pageableDto,@RequestParam String state) {
        pageableDto.setPage(page);
        pageableDto.setSize(size);
        List<TransactionResponseDTO> content = Collections.singletonList(transactionService.getTrasactionByState(state));
        Map<String, Object> response = Map.of("message", "Listado de Transacciones por Estado", "data", content);
        return new ResponseEntity<>(response, OK);
    }
    @GetMapping("/search/type")
    public ResponseEntity<?> getTransactionsByType(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "4") Integer size, PageableDto pageableDto,@PathVariable String type) {
        pageableDto.setPage(page);
        pageableDto.setSize(size);
        List<TransactionResponseDTO> content = Collections.singletonList(transactionService.getTrasactionByTypeTransaction(type));
        Map<String, Object> response = Map.of("message", "Listado de Transacciones por Tipo", "data", content);
        return new ResponseEntity<>(response, OK);
    }
    @GetMapping("/search/date")
    public ResponseEntity<?> getTransactionsByDate(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "4") Integer size, PageableDto pageableDto,@PathVariable Date date ){
        pageableDto.setPage(page);
        pageableDto.setSize(size);
        List<TransactionResponseDTO> content = Collections.singletonList(transactionService.getTrasactionByDate(date));
        Map<String, Object> response = Map.of("message", "Listado de Transacciones por Tipo", "data", content);
        return new ResponseEntity<>(response, OK);
    }

}
