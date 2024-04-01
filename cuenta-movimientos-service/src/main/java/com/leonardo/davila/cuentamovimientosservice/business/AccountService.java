package com.leonardo.davila.cuentamovimientosservice.business;

import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;

public interface AccountService {

    Account save(Account cuenta);
    Account update(Integer numeroCuenta, Account cuenta);
    void deleteById(Integer numeroCuenta);
    Account getByNumeroCuenta(Integer numeroCuenta);
}
