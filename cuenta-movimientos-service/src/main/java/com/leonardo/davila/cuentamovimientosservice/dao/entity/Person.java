package com.leonardo.davila.cuentamovimientosservice.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "persona")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
