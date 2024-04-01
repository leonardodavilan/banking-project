package com.leonardo.davila.cuentamovimientosservice.business.impl;

import com.leonardo.davila.cuentamovimientosservice.business.AccountService;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import com.leonardo.davila.cuentamovimientosservice.dao.repository.AccountDao;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao cuentaDao;

    public AccountServiceImpl(AccountDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public Account save(Account cuenta) {
        return cuentaDao.save(cuenta);
    }

    @Override
    public Account update(Integer numeroCuenta, Account cuenta) {

        Account cuentaActualizado = cuentaDao.findById(numeroCuenta).orElse(null);
        if (cuentaActualizado != null) {
            cuentaActualizado.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaActualizado.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaActualizado.setEstado(cuenta.isEstado());
            cuentaActualizado.setCliente(cuenta.getCliente());
            return cuentaActualizado;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Integer numeroCuenta) {
        Account cuenta = cuentaDao.findById(numeroCuenta).orElse(null);
        if (cuenta != null) {
            cuentaDao.deleteById(numeroCuenta);
        }else {
            throw new RuntimeException("Cuenta no encontrado");
        }
    }

    @Override
    public Account getByNumeroCuenta(Integer numeroCuenta) {
        return cuentaDao.findById(numeroCuenta).orElse(null);
    }

}
