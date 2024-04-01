package com.leonardo.davila.cuentamovimientosservice.dao.repository;

import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Movement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementDao extends CrudRepository<Movement, Long> {


    List<Movement> findByCuentaAndFechaBetween(Account cuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
