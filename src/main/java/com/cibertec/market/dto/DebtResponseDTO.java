package com.cibertec.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebtResponseDTO {
    private Long id;

    private String stallName;
    private String chargeName;
    private BigDecimal amount;
    private String period;
    private Boolean paid;
}
