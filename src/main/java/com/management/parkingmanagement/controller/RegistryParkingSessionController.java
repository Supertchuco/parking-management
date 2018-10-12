package com.management.parkingmanagement.controller;

import com.management.parkingmanagement.repository.ParkRepository;
import com.management.parkingmanagement.service.ParkingSessionService;
import com.management.parkingmanagement.vo.FinishingSessionVO;
import com.management.parkingmanagement.vo.StartingSessionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/assets")
public class RegistryParkingSessionController {

    @Autowired
    ParkingSessionService parkingSessionService;

    @Autowired
    ParkRepository parkRepository;


    @RequestMapping(value = "/{parkId}/sessions", method = RequestMethod.POST)
    public ResponseEntity startingParkingSession(@RequestBody StartingSessionVO startingSessionVO, @PathVariable String parkId) {
        parkingSessionService.createParkingSession(startingSessionVO, parkId);
        return new ResponseEntity("Access to be granted.", HttpStatus.OK);
    }

    @RequestMapping(value = "/{parkId}/vehicle/{licencePlateNumber}/session", method = RequestMethod.POST)
    public ResponseEntity finishingParkingSession(@RequestBody FinishingSessionVO finishingSessionVO, @PathVariable String parkId, @PathVariable String licencePlateNumber) {
        parkingSessionService.finishParkingSession(finishingSessionVO, parkId, licencePlateNumber);
        return new ResponseEntity("Session is stopped and bar/gate to be opened.", HttpStatus.OK);
    }

}
