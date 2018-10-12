package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.Park;
import org.springframework.data.repository.CrudRepository;

public interface ParkRepository extends CrudRepository<Park, Long> {

    Park findById(final int parkId);

}
