package com.leonardo.davila.cuentamovimientosservice.dao.repository;

import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {

    List<Account> findByCliente_ClienteIdAndEstado(Long clienteId, boolean estado);
}
