package com.cibertec.market.dto;

import com.cibertec.market.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    private Long accountId;
    private BigDecimal amount;
    private TransactionType type;
    private String description;
}
