package com.management.parkingmanagement.unit.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.exception.ParkIsNotActiveException;
import com.management.parkingmanagement.exception.ParkNotFoundException;
import com.management.parkingmanagement.exception.ParkServiceException;
import com.management.parkingmanagement.repository.ParkRepository;
import com.management.parkingmanagement.service.ParkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParkServiceTest {

    @InjectMocks
    private ParkService parkService;

    @Mock
    private ParkRepository parkRepository;
    private Park park;

    @Before
    public void setUp() {
        park = new Park(1, "name", "street", true, null);
    }

    @Test
    public void findParkByParkIdTestWhenParkIsFound() {
        Mockito.when(parkRepository.findById(Mockito.anyInt())).thenReturn(park);
        assertEquals(park, parkService.findParkByParkId(1));
    }

    @Test(expected = ParkServiceException.class)
    public void findParkByParkIdTestWhenExceptionOccurred() {
        doThrow(IllegalStateException.class)
                .when(parkRepository).findById(anyInt());
        parkService.findParkByParkId(1);
    }

    @Test
    public void validateParkTestHappyScenario() {
        parkService.validatePark(park);
    }

    @Test(expected = ParkIsNotActiveException.class)
    public void validateParkTestWhenParkIsNotActive() {
        park.setActive(false);
        parkService.validatePark(park);
    }

    @Test(expected = ParkNotFoundException.class)
    public void validateParkTestWhenParkIsNull() {
        parkService.validatePark(null);
    }
}
