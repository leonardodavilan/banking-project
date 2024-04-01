package com.leonardo.davila.cuentamovimientosservice.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "cliente")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;
    private String contrasena;
    private boolean estado;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id")
    private Person persona;
}
