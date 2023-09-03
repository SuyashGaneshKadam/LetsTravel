package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDTOs.BookTicket;
import com.SK.LetsTravel.Services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PutMapping("/bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicket bookTicket){
        try {
            return new ResponseEntity(bookingService.bookATicket(bookTicket), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Unable to book the flight : ", e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/cancelTicket")
    public ResponseEntity cancelTicket(@RequestParam Integer transportId, @RequestParam String seatNo){
        try{
            return new ResponseEntity<>(bookingService.cancelTicket(transportId, seatNo), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Some error occurred : ", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
