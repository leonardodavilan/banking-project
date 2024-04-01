package com.leonardo.davila.cuentamovimientosservice.business.impl;

import com.leonardo.davila.cuentamovimientosservice.business.MovementService;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Movement;
import com.leonardo.davila.cuentamovimientosservice.dao.repository.AccountDao;
import com.leonardo.davila.cuentamovimientosservice.dao.repository.MovementDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MovementServiceImpl implements MovementService {

	private final MovementDao movementDao;
	private final AccountDao accountDao;

	public MovementServiceImpl(MovementDao movementDao, AccountDao accountDao) {
		this.movementDao = movementDao;
		this.accountDao = accountDao;
	}

	@Override
	public Movement save(Movement cuenta) {
		return movementDao.save(cuenta);
	}

	@Override
	public Movement update(Long movimientoId, Movement movimiento) {

		Movement movimientoActualizado = movementDao.findById(movimientoId).orElse(null);
		if (movimientoActualizado != null) {
			movimientoActualizado.setFecha(movimiento.getFecha());
			movimientoActualizado.setTipoMovimiento(movimiento.getTipoMovimiento());
			movimientoActualizado.setValor(movimiento.getValor());
			movimientoActualizado.setSaldo(movimiento.getSaldo());
			return movimientoActualizado;
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(Long movimientoId) {
		Movement movimiento = movementDao.findById(movimientoId).orElse(null);
		if (movimiento != null) {
			movementDao.deleteById(movimientoId);
		}else {
			throw new RuntimeException("Movimiento no encontrado");
		}
	}

	@Override
	public Movement getById(Long movimientoId) {
		return movementDao.findById(movimientoId).orElse(null);
	}

	@Override
	public Movement withdraw(int accountNumber, double amount) {
		Account account = accountDao.findById(accountNumber)
				.orElseThrow(() -> new IllegalArgumentException("Account not found"));
		double newBalance = account.getSaldoInicial() - amount;
		if (newBalance < 0) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		account.setSaldoInicial(newBalance);
		accountDao.save(account);
		return createMovement(account, "Retiro", BigDecimal.valueOf(-amount));
	}

	@Override
	public Movement deposit(int accountNumber, double amount) {
		Account account = accountDao.findById(accountNumber)
				.orElseThrow(() -> new IllegalArgumentException("Account not found"));
		double newBalance = account.getSaldoInicial() + amount;
		account.setSaldoInicial(newBalance);
		accountDao.save(account);
		return createMovement(account, "Deposito", BigDecimal.valueOf(amount));
	}

	private Movement createMovement(Account account, String type, BigDecimal amount) {
		Movement movement = new Movement();
		movement.setFecha(LocalDateTime.now());
		movement.setTipoMovimiento(type);
		movement.setValor(amount);
		movement.setSaldo(BigDecimal.valueOf(account.getSaldoInicial()));
		movement.setCuenta(account);
		movementDao.save(movement);

		Movement movementDTO = new Movement();
		movementDTO.setFecha(movement.getFecha());
		movementDTO.setTipoMovimiento(movement.getTipoMovimiento());
		movementDTO.setValor(movement.getValor());
		movementDTO.setSaldo(movement.getSaldo());
		return movementDTO;
	}
}
