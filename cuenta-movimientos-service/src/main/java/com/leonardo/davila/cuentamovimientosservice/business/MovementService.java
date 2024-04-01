package com.leonardo.davila.cuentamovimientosservice.business;

import com.leonardo.davila.cuentamovimientosservice.dao.entity.Movement;

public interface MovementService {

    Movement save(Movement movimiento);
    Movement update(Long movimientoId, Movement movimiento);
    void deleteById(Long movimientoId);
    Movement getById(Long movimientoId);
    Movement withdraw(int accountNumber, double amount);
    Movement deposit(int accountNumber, double amount);
}
