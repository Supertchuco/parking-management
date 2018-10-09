package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.repository.ParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParkService {

    @Autowired
    ParkRepository parkRepository;

    public Park findParkByParkName(final String parkName) {
        return parkRepository.findByParkName(parkName);
    }
}
