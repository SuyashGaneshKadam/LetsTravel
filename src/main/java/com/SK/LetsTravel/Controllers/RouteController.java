package com.SK.LetsTravel.Controllers;

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

    @PostMapping("/addRoute")
    public ResponseEntity addRoute(@RequestBody AddRoute requestDto){
        try {
            return new ResponseEntity<>(routeService.addRoute(requestDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add Route : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
