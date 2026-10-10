package com.example.FirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// It is a controller that handles HTTP requests and returns responses
@RestController 
public class car {
    
    // Dependency Injection ( It is a mechanism by which a class can receive instances of other classes that it depends on)
    @Autowired 
    private Dog dog;

    //get request 
    @GetMapping("/ok")
    public String ok(){
        return dog.fun();
    }

}
