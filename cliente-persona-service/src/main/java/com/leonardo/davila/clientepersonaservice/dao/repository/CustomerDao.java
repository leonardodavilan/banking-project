package com.leonardo.davila.clientepersonaservice.dao.repository;

import com.leonardo.davila.clientepersonaservice.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

}
