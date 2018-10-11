package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.entities.ParkingSession;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.exception.ParkingSessionNotFoundException;
import com.management.parkingmanagement.exception.ParkingSessionServiceException;
import com.management.parkingmanagement.exception.PendentParkSessionException;
import com.management.parkingmanagement.repository.ParkingSessionRepository;
import com.management.parkingmanagement.vo.FinishingSessionVO;
import com.management.parkingmanagement.vo.StartingSessionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

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
        log.info("Get total minutes");
        return Minutes.minutesBetween(new DateTime(initDate), new DateTime(finalDate)).getMinutes();
    }

    private BigDecimal calculateValueToPay(final int minutes, final BigDecimal valueTo15Minutes) {
        log.info("Calculate value to pay");
        int timeFrames = minutes / 15;
        if (timeFrames == 0) {
            timeFrames = 1;
        }
        return new BigDecimal(timeFrames).multiply(valueTo15Minutes);
    }

    public boolean createParkingSession(final StartingSessionVO startingSessionVO, final String parkId) {
        log.info("create parking session for this park id {} and number plate {}", parkId, startingSessionVO.getLicensePlateNumber());
        Vehicle vehicle = vehicleService.findByPlateNumber(startingSessionVO.getLicensePlateNumber());
        checkIfExistSessionOpenedByPlateNumber(vehicle.getPlateNumber());
        vehicleService.validateVehicle(vehicle);
        Park park = parkService.findParkByParkId(Integer.parseInt(parkId));
        parkService.validatePark(park);
        try {
            ParkingSession parkingSession = new ParkingSession(new Date(), vehicle.getPlateNumber(), park.getId());
            parkingSessionRepository.save(parkingSession);
        } catch (Exception e) {
            log.error("Error to save parking session", e);
            throw new ParkingSessionServiceException("Error to save parking session");
        }
        log.info("Session created");
        return true;
    }

    public boolean finishParkingSession(FinishingSessionVO finishingSessionVO, String parkId, String plateNumber) {
        log.info("finish parking session for this park id {} and number plate {}", parkId, plateNumber);
        ParkingSession parkingSession = parkingSessionRepository.getOpenParkingSessionByParkIdAndPlateNumber(plateNumber, Integer.parseInt(parkId));

        if (Objects.isNull(parkingSession)) {
            log.error("Pendent session not found for this plate number: {} and this park id: {}", plateNumber, parkId);
            throw new ParkingSessionNotFoundException("Error to save parking session");
        }

        Vehicle vehicle = vehicleService.findByPlateNumber(plateNumber);
        vehicleService.validateVehicle(vehicle);

        Park park = parkService.findParkByParkId(Integer.parseInt(parkId));
        BigDecimal invoice;

        try {
            Date finishDate = new Date();
            int totalMinutes = getTotalMinutesInParking(parkingSession.getStarted(), finishDate);
            BigDecimal finalPriceToPay = calculateValueToPay(totalMinutes, park.getPrice().getPriceFor15Minutes());

            invoice = clientService.updateDebitFromClientAccountBalance(finalPriceToPay, vehicle.getClient());
            parkingSession.setFinished(finishDate);
            parkingSessionRepository.save(parkingSession);
        } catch (Exception e) {
            log.error("Error during finish parking session", e);
            throw new ParkingSessionServiceException(e.getMessage());
        }

        if (invoice.compareTo(BigDecimal.ZERO) > 0) {
            emailService.sendHTMLEmail(vehicle.getClient().getEmail(), emailService.buildEmailBody(invoice, parkingSession, park, vehicle), "Parking Session Invoice");
        }

        log.info("Session finished");
        return true;
    }

    private void checkIfExistSessionOpenedByPlateNumber(String plateNumber) {
        Collection<ParkingSession> parkingSessions = parkingSessionRepository.findAllOpenedParkingSessionsByPlateNumber(plateNumber);
        if (CollectionUtils.isNotEmpty(parkingSessions)) {
            log.error("vehicle with pendent parking session");
            throw new PendentParkSessionException("vehicle with opened parking session");
        }
    }

    public Collection<ParkingSession> findAll() {

        return parkingSessionRepository.findAll();
    }

}
