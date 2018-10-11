package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.exception.ParkIsNotActiveException;
import com.management.parkingmanagement.exception.ParkNotFoundException;
import com.management.parkingmanagement.exception.ParkServiceException;
import com.management.parkingmanagement.repository.ParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ParkService {

    @Autowired
    ParkRepository parkRepository;

    public Park findParkByParkId(final int parkId) {
        log.info("Find park by id");
        try {
            return parkRepository.findById(parkId);
        } catch (Exception e) {
            log.error("Intern error to find park", e);
            throw new ParkServiceException("Intern error to find park");
        }

    }

    public void validatePark(final Park park) {
        if (Objects.isNull(park) || !park.isActive()) {
            log.error("park not found");
            throw new ParkNotFoundException("park not found");
        }

        if (!park.isActive()) {
            log.error("park is not active");
            throw new ParkIsNotActiveException("park is not active");
        }
    }
}
