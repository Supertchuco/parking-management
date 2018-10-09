package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.ParkingSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSessionRepository extends CrudRepository<ParkingSession, Long> {
}
