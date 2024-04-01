package com.leonardo.davila.clientepersonaservice.business;

import com.leonardo.davila.clientepersonaservice.dao.entity.Customer;

public interface CustomerService {

    Customer save(Customer cliente);
    Customer update(Long clienteId, Customer cliente);
    void deleteById(Long clienteId);
    Customer getById(Long clienteId);
}
