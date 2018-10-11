package com.management.parkingmanagement.unit.service;

import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.exception.VehicleNotFoundException;
import com.management.parkingmanagement.exception.VehicleServiceException;
import com.management.parkingmanagement.repository.VehicleRepository;
import com.management.parkingmanagement.service.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    private Vehicle vehicle;

    @Before
    public void setUp() {
        vehicle = new Vehicle("test5599", "Tesla", null);
    }

    @Test
    public void findByPlateNumberTestWhenVehicleIsFound() {
        Mockito.when(vehicleRepository.findByPlateNumber(anyString())).thenReturn(vehicle);
        assertEquals(vehicle, vehicleService.findByPlateNumber(anyString()));
    }

    @Test(expected = VehicleServiceException.class)
    public void findByPlateNumberTestWhenExceptionOccurred() {
        doThrow(IllegalStateException.class)
                .when(vehicleRepository).findByPlateNumber(anyString());
        vehicleService.findByPlateNumber(anyString());
    }

    @Test
    public void validateVehicleTestHappyScenario() {
        vehicleService.validateVehicle(vehicle);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void validateVehicleTestWhenVehicleIsNotFound() {
        vehicleService.validateVehicle(null);
    }
}
