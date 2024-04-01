package com.leonardo.davila.cuentamovimientosservice.business.impl;

import com.leonardo.davila.cuentamovimientosservice.business.ReportService;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Movement;
import com.leonardo.davila.cuentamovimientosservice.dao.repository.AccountDao;
import com.leonardo.davila.cuentamovimientosservice.dao.repository.MovementDao;
import com.leonardo.davila.cuentamovimientosservice.expose.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final AccountDao accountRepository;
    private final MovementDao movementRepository;

    @Autowired
    public ReportServiceImpl(AccountDao accountRepository, MovementDao movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public ReportDTO generateReport(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        List<Account> accounts = accountRepository.findByCliente_ClienteIdAndEstado(clienteId, true);
        for (Account account : accounts) {
            List<Movement> movimientos = movementRepository.findByCuentaAndFechaBetween(account, fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59));
            account.setMovimientos(movimientos);
        }
        return new ReportDTO(accounts);
    }
}
