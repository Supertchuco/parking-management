package com.management.parkingmanagement.repository;

import com.management.parkingmanagement.entities.ParkingSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ParkingSessionRepository extends CrudRepository<ParkingSession, Long> {

    @Query("SELECT parkingSession FROM ParkingSession parkingSession WHERE parkingSession.plateNumber = :plateNumber " +
            "AND parkingSession.parkId = :parkId AND  parkingSession.finished is null")
    ParkingSession getOpenParkingSessionByParkIdAndPlateNumber(final @Param("plateNumber") String plateNumber, final @Param("parkId") int parkId);

    @Query("SELECT parkingSession FROM ParkingSession parkingSession WHERE parkingSession.plateNumber = :plateNumber " +
            "AND  parkingSession.finished is null")
    Collection<ParkingSession> findAllOpenedParkingSessionsByPlateNumber(final @Param("plateNumber") String plateNumber);
}
