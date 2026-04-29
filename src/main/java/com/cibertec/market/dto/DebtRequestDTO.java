package com.cibertec.market.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebtRequestDTO {
    private Long id;

    @NotNull(message = "El ID del puesto es obligatorio")
    private Long stallId;

    @NotNull(message = "El ID del cargo es obligatorio")
    private Long chargeId;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año no puede ser anterior a 1900")
    private Integer year;

    @NotNull(message = "El mes es obligatorio")
    @Min(value = 1, message = "El mes debe ser como mínimo 1 (Enero)")
    @Max(value = 12, message = "El mes debe ser como máximo 12 (Diciembre)")
    private Integer month;

    private BigDecimal amount;
}
