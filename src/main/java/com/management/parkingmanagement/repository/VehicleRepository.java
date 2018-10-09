package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Collection<Vehicle> findByNumberPlate(String numberPlate);
}
