package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDtos.AddTransportRequestDto;
import com.SK.LetsTravel.Services.TransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportService transportService;

    @PostMapping("/addTransport")
    public ResponseEntity addTransport(@RequestBody AddTransportRequestDto requestDto){
        try{
            return new ResponseEntity<>(transportService.addTransport(requestDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add Transport ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
