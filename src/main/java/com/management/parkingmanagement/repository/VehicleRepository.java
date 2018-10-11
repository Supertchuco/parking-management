package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Vehicle findByPlateNumber(String plateNumber);
}
