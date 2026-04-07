package com.cibertec.market.model;

import com.cibertec.market.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    @ManyToOne
    private DocumentType documentType;

    @ManyToOne
    private Stall stall;

    private LocalDate date;
}
