package com.nocountry.cashier.controller.rest;

import com.nocountry.cashier.controller.dto.request.BillRequestDTO;
import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.TransactionRequestDTO;
import com.nocountry.cashier.controller.dto.response.BillResponseDTO;
import com.nocountry.cashier.domain.usecase.BillService;
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

import java.util.Map;

import static com.nocountry.cashier.util.Constant.*;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:4200", "*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping(value = API_VERSION + RESOURCE_USER + RESOURECE_TRANSACTION)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Management Transaction Bill", description = "Transaction Bill API")
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);
    private final BillService billService;

    @Operation(
            description = "Created Transaction Bill",
            summary = "Crea un Pago de una Factura  ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BillRequestDTO.class))}
                    )
            }
    )
    @PostMapping("/bill_pay")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody
                                                   @Parameter(description = "DTO el cual permite tomar datos para el pago de una factura",
                                                           schema = @Schema(implementation = BillRequestDTO.class))
                                                   BillRequestDTO billRequestDTO){

        BillResponseDTO billResponseDTO = billService.createBill(billRequestDTO);
        return ResponseEntity.status(OK).body(billResponseDTO);

    }

    @Operation(
            description = "Get All Bills",
            summary = "Recupera todas las facturas",
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
    @GetMapping("/bills")
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
            Page<BillResponseDTO> content = billService.findAllByIdAccount(idAccount,pageableDto);
            Map<String,Object> response = Map.of("message", "Listado de Facturas", "data", content);
            return new ResponseEntity<>(response,OK);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));

        }
    }
    @Operation(
            description = "Get All Bills",
            summary = "Recupera todas las facturas Por Id De Cuenta",
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
    @GetMapping("bills_type")
    public  ResponseEntity<?> findByType(@Parameter(description = "Tipos de facturas a buscar(agua , luz,gas)")@RequestParam  String bill_type,
                                         @Parameter(description = "Id de la cuenta para ver sus movimientos", required = true) @RequestParam String idAccount,
                                         @Parameter (description = "Página de donde comenzar") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @Parameter(description = "Cantidad de valores por página") @RequestParam(value = "size", defaultValue = "4") Integer size,
                                         @Parameter(description = "Orden de la paginación, donde 0 DESC - 1 ASC") @RequestParam(value = "order", defaultValue = "1") Integer order,
                                         @Parameter(description = "Campo de la entidad por la cual quieres ordenar") @RequestParam(value = "field", defaultValue = "id") String field)
                                        {
        try {
            PageableDto pageableDto = new PageableDto();
            pageableDto.setPage(page);
            pageableDto.setSize(size);
            pageableDto.setOrder(order);
            pageableDto.setField(field);
            Page<BillResponseDTO> content = billService.findByType(bill_type,idAccount,pageableDto);
            Map<String,Object> response = Map.of("message", "Listado de Facturas", "data", content);
            return new ResponseEntity<>(response,OK);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "}"));
        }
    }

}
