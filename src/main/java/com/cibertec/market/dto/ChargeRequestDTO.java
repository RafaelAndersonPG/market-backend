package com.cibertec.market.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequestDTO {
    private Long id;

    @NotBlank(message = "Ingrese los nombres")
    private String name;

    @NotBlank(message = "Ingrese la descripción")
    private String description;
}
