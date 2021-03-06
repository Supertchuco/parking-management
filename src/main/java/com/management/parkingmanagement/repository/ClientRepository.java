package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByEmail(final String email);
}
