package com.cibertec.market.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private Long paymentId;
    private String stallName;
    private String chargeName;
    private BigDecimal amount;
    private LocalDateTime date;
}
