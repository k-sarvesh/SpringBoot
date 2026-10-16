package com.Sarvesh.JournalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sarvesh.JournalApp.Entity.User;
import com.Sarvesh.JournalApp.Service.UserServices;

@RestController 
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserServices userServices;
    
    @GetMapping 
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAll(), HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<?> addUser(@RequestBody User user){
        userServices.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User userInDb = userServices.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServices.saveUser(userInDb); 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
