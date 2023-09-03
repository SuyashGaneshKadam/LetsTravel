package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.Enums.City;
import com.SK.LetsTravel.RequestDTOs.AddTransport;
import com.SK.LetsTravel.Services.TransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportService transportService;

    @PostMapping("/add")
    public ResponseEntity addTransport(@RequestBody AddTransport requestDto){
        try{
            return new ResponseEntity<>(transportService.addTransport(requestDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add Transport : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/searchFlights")
    public ResponseEntity searchFlights(@RequestParam City fromCity, @RequestParam City toCity, @RequestParam LocalDate journeyDate){
        try {
            return new ResponseEntity(transportService.searchFlights(fromCity,toCity,journeyDate),HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Unable to get the flight details : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getAvailableSeats")
    public ResponseEntity getAvailableSeats(@RequestParam LocalDate journeyDate, @RequestParam Integer transportId){
        try{
            return new ResponseEntity<>(transportService.getAvailableSeats(journeyDate, transportId), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Unable to get available seats : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/totalRevenueEarnedByCompanyInAGivenMonthAndYear")
    public ResponseEntity totalRevenueEarned(@RequestParam String companyName, @RequestParam Integer month, @RequestParam Integer year){
        try {
            return new ResponseEntity(transportService.totalRevenueEarned(companyName, month, year), HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Some error Occurred : ", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}