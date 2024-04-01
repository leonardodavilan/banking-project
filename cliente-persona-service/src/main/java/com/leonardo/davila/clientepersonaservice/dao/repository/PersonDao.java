package com.leonardo.davila.clientepersonaservice.dao.repository;

import com.leonardo.davila.clientepersonaservice.dao.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {

}
