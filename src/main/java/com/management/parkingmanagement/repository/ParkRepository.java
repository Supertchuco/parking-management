package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Park;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ParkRepository extends CrudRepository<Park, Long> {

    Park findById(int parkId);

    Collection<Park> findAll();

}
