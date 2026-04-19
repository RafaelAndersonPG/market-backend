package com.cibertec.market.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StallRequestDTO {
    private Long id;

    @NotBlank(message = "Número del puesto no debe estar vacío")
    private String name;

}
