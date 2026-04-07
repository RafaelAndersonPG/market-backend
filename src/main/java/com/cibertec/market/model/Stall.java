package com.cibertec.market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Stall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Número del puesto no debe ser nulo")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
