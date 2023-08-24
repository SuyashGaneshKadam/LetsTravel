package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDtos.AddRouteRequestDto;
import com.SK.LetsTravel.Services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping("/addRoute")
    public ResponseEntity addRoute(@RequestBody AddRouteRequestDto requestDto){
        try {
            return new ResponseEntity<>(routeService.addRoute(requestDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add Route", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
