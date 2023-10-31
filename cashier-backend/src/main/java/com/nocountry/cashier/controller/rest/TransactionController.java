package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.GenericResponseDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.domain.usecase.TransactionService;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.nocountry.cashier.util.Constant.*;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:4200", "*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping(value = API_VERSION + RESOURCE_USER + RESOURECE_TRANSACTION)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Management Transaction", description = "Transaction API")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    //GetALLTransactions
    //http://localhost:8080/v1/api/customers/transactions?page=0&size=4&order=1&field=id
    @Operation(
            description = "Get All Transactions",
            summary = "Recupera todas las transacciones",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Page.class))}
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getAllTransactions(@Parameter(description = "Id de la cuenta para ver sus movimientos", required = true) @RequestParam String idAccount,
                                                @Parameter(description = "Página de donde comenzar") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @Parameter(description = "Cantidad de valores por página") @RequestParam(value = "size", defaultValue = "4") Integer size,
                                                @Parameter(description = "Orden de la paginación, donde 0 DESC - 1 ASC") @RequestParam(value = "order", defaultValue = "1") Integer order,
                                                @Parameter(description = "Campo de la entidad por la cual quieres ordenar") @RequestParam(value = "field", defaultValue = "id") String field) throws Exception {
        try {
            PageableDto pageableDto = new PageableDto();
            pageableDto.setPage(page);
            pageableDto.setSize(size);
            pageableDto.setOrder(order);
            pageableDto.setField(field);
            Page<TransactionResponseDTO> content = transactionService.findAllByIdAccount(idAccount, pageableDto);
            Map<String, Object> response = Map.of("message", "Listado de Transacciones", "data", content);
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }

    }


    @Operation(
            description = "Created Transaction",
            summary = "Crea una transferencia de un usuario a otro",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class))}
                    )
            }
    )
    //NewTransaction
    //http://localhost:8080/v1/api/customers/transactions/new?idAccount=3de8f7f3-41a6-404c-ad4e-599bd9e74e98
    @PostMapping("/new")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody @Parameter(description = "DTO el cual permite tomar datos para una transferencia", schema = @Schema(implementation = TransactionRequestDTO.class)) TransactionRequestDTO requestDTO) {
        TransactionResponseDTO transactionResponse = transactionService.createTransaction(requestDTO);
        return ResponseEntity.status(OK).body(transactionResponse);

    }

    //SearchById
    //http://localhost:8080/v1/api/customers/transactions/search/58c6f82a-57f0-4b74-ba56-2dfcd6665a54
    @GetMapping("/search")
    public ResponseEntity<?> getTransactionById(@RequestParam String id, @RequestParam String idAccount) throws Exception {
        try {
            return ResponseEntity.ok(new GenericResponseDTO<>(true, "Transaccion Encontrada", transactionService.findOneByIdAccount(id, idAccount)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }
    }

    //SearchByState
    //http://localhost:8080/v1/api/customers/transactions/search/state?idAccount=a37cf234-fad2-44f0-95e2-e17a532939f4&state=DONE&page=0&size=4&order=1&field=id
    @GetMapping("/search/state")
    public ResponseEntity<?> getTransactionsByState(@RequestParam String idAccount,
                                                    @RequestParam EnumsState state,
                                                    @Parameter(schema = @Schema(implementation = PageableDto.class)) PageableDto pageableDto) {
//        pageableDto.setPage(page);
//        pageableDto.setSize(size);

        try {
            Page<TransactionResponseDTO> content = transactionService.findByState(state, idAccount, pageableDto);
            Map<String, Object> response = Map.of("message", "Listado Por Estado De Transaccion", "data", content);
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }


    }

    //SearchByType
    //http://localhost:8080/v1/api/customers/transactions/search/type?idAccount=a37cf234-fad2-44f0-95e2-e17a532939f4&type=DEPOSIT&page=0&size=4&order=1&field=id
    @GetMapping("/search/type")
    public ResponseEntity<?> getTransactionsByType(@RequestParam String idAccount,
                                                   @RequestParam EnumsTransactions type,
                                                   PageableDto pageableDto) {
//        pageableDto.setPage(page);
//        pageableDto.setSize(size);
        try {
            Page<TransactionResponseDTO> content = transactionService.findByType(type, idAccount, pageableDto);
            Map<String, Object> response = Map.of("message", "Listado Por Tipo De Transacccion", "data", content);
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }

    }

    //SearchByAmount
    //http://localhost:8080/v1/api/customers/transactions/search/amount?idAccount=a37cf234-fad2-44f0-95e2-e17a532939f4&amount=900&page=0&size=4&order=1&field=id
    @GetMapping("/search/amount")
    public ResponseEntity<?> getTransactionsByAmount(@RequestParam String idAccount,
                                                     @RequestParam BigDecimal amount,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "4") Integer size,
                                                     PageableDto pageableDto) {

        try {
            Page<TransactionResponseDTO> content = transactionService.findByAmount(amount, idAccount, pageableDto);
            Map<String, Object> response = Map.of("message", "Listado Por De Transacciones Por Monto", "data", content);
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));

        }

    }


}
