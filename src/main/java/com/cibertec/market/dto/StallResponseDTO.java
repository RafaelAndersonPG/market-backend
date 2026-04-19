package com.cibertec.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StallResponseDTO {
    private Long id;
    private String name;
    private String ownerName;
}
