package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDTOs.*;
import com.SK.LetsTravel.Services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;
    @PostMapping("/addFlightSeats")
    public ResponseEntity addFlightSeats(@RequestBody AddFlightSeats addFlightSeats){
        try {
            return new ResponseEntity<>(seatService.addFlightSeats(addFlightSeats), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add seats : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/addBusSeats")
    public ResponseEntity addBusSeats(@RequestBody AddBusSeats addBusSeats){
        try {
            return new ResponseEntity<>(seatService.addBusSeats(addBusSeats), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add seats : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
