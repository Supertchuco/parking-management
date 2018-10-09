package com.management.parkingmanagement.service;

import com.management.parkingmanagement.entities.Park;
import com.management.parkingmanagement.entities.ParkingSession;
import com.management.parkingmanagement.entities.Vehicle;
import com.management.parkingmanagement.repository.ParkingSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private ParkingSessionRepository parkingSessionRepository;

    private int getDifferenceInMinutes(final Date initDate, final Date finalDate) {
        //maybe need to %60
        return Minutes.minutesBetween(new DateTime(initDate), new DateTime(finalDate)).getMinutes();
    }

    private BigDecimal calculateValueToPay(final int minutes, final BigDecimal valueTo15Minutes) {
        int timeFrames = minutes / 15;
        return new BigDecimal(timeFrames).multiply(valueTo15Minutes);
    }

    private boolean createParkingSession() {

        Vehicle vehicle = vehicleService.findByNumberPlate("numberPlate");

        if (Objects.isNull(vehicle)) {
            // carro não encontrado erro
        }

        Park park = parkService.findParkByParkName("park name");

        if (Objects.isNull(park) || !park.isActive()) {
            // não encontrado erro ou não ativo
        }

        ParkingSession parkingSession = new ParkingSession(new Date(), vehicle, park);
        parkingSessionRepository.save(parkingSession);

        return true;
    }



    private boolean finishParkingSession(){

        ParkingSession parkingSession = null;//parkingSessionRepository.fi

        Date finishDate = new Date();
        int totalMinutes = getDifferenceInMinutes(parkingSession.getStarted(), new Date());
        BigDecimal finalPriceToPay = calculateValueToPay(totalMinutes, parkingSession.getPark().getPrice().getPriceFor15Minutes());



        return true;
    }

}
