package com.cibertec.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String number;
    private BigDecimal balance;
}
