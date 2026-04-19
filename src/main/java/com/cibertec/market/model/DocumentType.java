package com.cibertec.market.model;

import com.cibertec.market.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tipo de documento no puede ser nulo")
    @Column(unique = true, nullable = false)
    private DocumentTypeEnum type;

    private String description;
}
