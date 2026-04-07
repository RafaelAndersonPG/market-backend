package com.cibertec.market.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;
    private Integer month;

    private BigDecimal amount;

    private String description;

    private Boolean paid = false;

    @ManyToOne
    private Stall stall;
}
