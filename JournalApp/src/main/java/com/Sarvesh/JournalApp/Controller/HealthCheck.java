package com.Sarvesh.JournalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HealthCheck {

    @GetMapping("/health-check") // Here we map the get request to the health check method
    public String healthCheck(){
        return "Ok";
    }
}
