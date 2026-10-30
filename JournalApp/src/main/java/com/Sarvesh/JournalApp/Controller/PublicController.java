package com.Sarvesh.JournalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sarvesh.JournalApp.Entity.User;
import com.Sarvesh.JournalApp.Service.UserServices;

@RestController 
@RequestMapping("/public")
public class PublicController {

    @Autowired 
    private UserServices userServices;

    @GetMapping("/health-check") // Here we map the get request to the health check method
    public String healthCheck(){
        return "Ok";
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        userServices.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
