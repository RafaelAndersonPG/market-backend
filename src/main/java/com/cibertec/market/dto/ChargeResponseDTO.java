package com.cibertec.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeResponseDTO {
    private Long id;

    private String name;

    private String description;

    private BigDecimal amount;

}
