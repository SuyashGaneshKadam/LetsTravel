package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.Services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAvailableSeats")
    public ResponseEntity getAvailableSeats(@RequestParam LocalDate journeyDate, @RequestParam Integer transportId){
        try{
            return new ResponseEntity<>(bookingService.getAvailableSeats(journeyDate, transportId), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Unable to get available seats : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
