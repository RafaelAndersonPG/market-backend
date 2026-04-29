package com.cibertec.market.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDTO{
    @NotNull
    private Long debtId;
    @NotNull
    private Long accountId;
    private String description;
}
