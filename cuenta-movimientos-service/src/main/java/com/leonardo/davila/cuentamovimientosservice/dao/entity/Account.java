package com.leonardo.davila.cuentamovimientosservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "cuenta")
public class Account {

    @Id
    @Column(name = "numero_cuenta")
    private int numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private double saldoInicial;
    private boolean estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Customer cliente;

    @JsonBackReference
    @OneToMany(mappedBy = "cuenta", fetch = FetchType.EAGER)
    private List<Movement> movimientos;
}
