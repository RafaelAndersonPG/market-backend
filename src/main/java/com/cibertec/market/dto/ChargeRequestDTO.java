package com.cibertec.market.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
