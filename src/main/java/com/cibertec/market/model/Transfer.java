package com.cibertec.market.model;

import com.cibertec.market.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private DocumentType documentType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "stall_id", nullable = false)
    private Stall stall;
}
