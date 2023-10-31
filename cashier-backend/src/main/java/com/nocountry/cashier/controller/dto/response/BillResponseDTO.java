package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({"id","dateEmit","bill_type","bill_num","amount", "voucher_num", "state"})
@JsonRootName(value = "data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private LocalDateTime dateEmit;
    private String type;
    private String bill_type ;
    private String bill_num ;
    private BigDecimal amount;
    private String voucher_num;
    private String state;
}
