package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;


    public Vehicle findByNumberPlate(final String numberPlate){
        return vehicleRepository.findByNumberPlate(numberPlate);
    }

}
