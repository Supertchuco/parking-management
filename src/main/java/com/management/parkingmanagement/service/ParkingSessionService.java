package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.entities.ParkingSession;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.exception.ParkingSessionServiceException;
import com.management.parkingmanagement.repository.ParkingSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class ParkingSessionService {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ParkService parkService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ParkingSessionRepository parkingSessionRepository;

    private int getTotalMinutesInParking(final Date initDate, final Date finalDate) {
        //maybe need to %60
        return Minutes.minutesBetween(new DateTime(initDate), new DateTime(finalDate)).getMinutes();
    }

    private BigDecimal calculateValueToPay(final int minutes, final BigDecimal valueTo15Minutes) {
        int timeFrames = minutes / 15;
        return new BigDecimal(timeFrames).multiply(valueTo15Minutes);
    }

    private boolean createParkingSession() {


        Vehicle vehicle = vehicleService.findByNumberPlate("numberPlate");

        vehicleService.validateVehicle(vehicle);

        Park park = parkService.findParkByParkName("park name");

        parkService.validatePark(park);

        try {
            ParkingSession parkingSession = new ParkingSession(new Date(), vehicle, park);
            parkingSessionRepository.save(parkingSession);
        } catch (Exception e) {
            log.error("Error to save parking session", e);
            throw new ParkingSessionServiceException("Error to save parking session");
        }


        return true;
    }


    public boolean finishParkingSession() {

        ParkingSession parkingSession = null;//parkingSessionRepository.fi

        Vehicle vehicle = vehicleService.findByNumberPlate("");

        vehicleService.validateVehicle(vehicle);

        int totalMinutes = getTotalMinutesInParking(parkingSession.getStarted(), new Date());
        BigDecimal finalPriceToPay = calculateValueToPay(totalMinutes, parkingSession.getPark().getPrice().getPriceFor15Minutes());

        BigDecimal debitRest = clientService.updateDebitFromClientAccountBalance(finalPriceToPay, vehicle.getClient());

        if (debitRest.compareTo(new BigDecimal(0)) == 1) {
            emailService.sendHTMLEmail(vehicle.getClient().getEmail(), "", "");
        }

        return true;
    }


}
