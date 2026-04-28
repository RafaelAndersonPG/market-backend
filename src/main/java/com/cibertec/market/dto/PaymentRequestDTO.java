package com.cibertec.market.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class PaymentRequestDTO{
    @NotNull
    private Long debtId;
}
