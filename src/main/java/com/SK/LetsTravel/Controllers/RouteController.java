package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.Enums.City;
import com.SK.LetsTravel.RequestDTOs.AddRoute;
import com.SK.LetsTravel.Services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping("/add")
    public ResponseEntity addRoute(@RequestBody AddRoute requestDto){
        try {
            return new ResponseEntity<>(routeService.addRoute(requestDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add Route : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/findShortestDurationTransportAndCheapestTransport")
    public ResponseEntity findCheapestTransport(@RequestParam City fromCity, @RequestParam City toCity){
        try{
            return new ResponseEntity<>(routeService.findCheapestTransport(fromCity,toCity), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Some error occurred : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
