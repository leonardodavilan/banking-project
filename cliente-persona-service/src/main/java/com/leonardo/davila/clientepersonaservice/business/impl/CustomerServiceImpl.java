package com.leonardo.davila.clientepersonaservice.business.impl;

import com.leonardo.davila.clientepersonaservice.business.CustomerService;
import com.leonardo.davila.clientepersonaservice.dao.entity.Customer;
import com.leonardo.davila.clientepersonaservice.dao.repository.CustomerDao;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao clienteDao;

    public CustomerServiceImpl(CustomerDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public Customer save(Customer cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public Customer update(Long clienteId, Customer cliente) {

        Customer clienteActualizado = clienteDao.findById(clienteId).orElse(null);

        if (clienteActualizado != null) {

            clienteActualizado.setContrasena(cliente.getContrasena());
            clienteActualizado.setEstado(cliente.isEstado());
            clienteActualizado.setPersona(cliente.getPersona());

            return clienteActualizado;

        } else {
            return null;
        }

    }

    @Override
    public void deleteById(Long clienteId) {

        Customer cliente = clienteDao.findById(clienteId).orElse(null);
        if (cliente != null) {
            clienteDao.deleteById(clienteId);
        }else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public Customer getById(Long clienteId) {
        return clienteDao.findById(clienteId).orElse(null);
    }
}
