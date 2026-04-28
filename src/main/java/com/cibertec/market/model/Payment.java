package com.cibertec.market.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @OneToOne
    @JoinColumn(name = "debt_id", nullable = false, unique = true)
    private Debt debt;
}