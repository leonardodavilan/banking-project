package com.leonardo.davila.cuentamovimientosservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movimiento")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long movimientoId;
    private LocalDateTime fecha;
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "numeroCuenta")
    private Account cuenta;
}
