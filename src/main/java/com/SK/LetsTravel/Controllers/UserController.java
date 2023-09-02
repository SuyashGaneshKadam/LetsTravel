package com.SK.LetsTravel.Controllers;

import com.SK.LetsTravel.RequestDTOs.AddUser;
import com.SK.LetsTravel.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody AddUser addUser){
        try {
            return new ResponseEntity<>(userService.addUser(addUser), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.error("Unable to add user : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/noOfUsersWhoBookedAtleastTwoSeatsInOneBooking")
    public ResponseEntity noOfUsers(){
        try {
            return new ResponseEntity<>(userService.noOfUsers(), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Some error occurred : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
