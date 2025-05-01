package com.lcwd.user.service.controller;


import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Rest template to communicate between services
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    //create

    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user){


        User user1= userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // get single user

    @GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User>gettingUser( @PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);


    }

    //creating fallback method for cirucitbreaker
    public ResponseEntity<User>ratingFallback(String userId, Exception ex){
        logger.info("FallBack is executed because service is down: ",ex.getMessage());
        User user=User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy bcz some services are down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //get all users

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);

    }
}
