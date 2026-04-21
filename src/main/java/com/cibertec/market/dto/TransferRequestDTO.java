package com.cibertec.market.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    private Long id;

    @NotNull(message = "ID del puesto es obligatorio")
    private Long stallId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Digits(integer = 10, fraction = 2, message = "Formato de monto inválido (máximo 2 decimales)")
    private BigDecimal amount;

    @NotBlank(message = "El nombre del nuevo dueño es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String ownerName;

    @NotBlank(message = "El DNI es obligatorio")
    private String ownerDni;

}
