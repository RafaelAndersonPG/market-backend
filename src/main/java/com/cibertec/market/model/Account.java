package com.cibertec.market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Número de cuenta no debe ser nulo")
    private String number;

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "stall_id", unique = true)
    private Stall stall;
}
