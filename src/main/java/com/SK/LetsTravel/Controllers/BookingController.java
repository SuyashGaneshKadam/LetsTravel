package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDTOs.BookSeatsInFlight;
import com.SK.LetsTravel.Services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PutMapping("/BookSeatsInFlight")
    public ResponseEntity BookSeatsInFlight(@RequestBody BookSeatsInFlight bookSeatsInFlight){
        try {
            return new ResponseEntity(bookingService.BookSeatsInFlight(bookSeatsInFlight), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Unable to book the flight : ", e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
