package com.Sarvesh.JournalApp.Controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sarvesh.JournalApp.Entity.User;
import com.Sarvesh.JournalApp.Repository.UserRepository;
import com.Sarvesh.JournalApp.Service.UserServices;

@RestController 
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserServices userServices;

    @Autowired 
    private UserRepository userRepository;
    
    @GetMapping 
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userServices.getAll(), HttpStatus.OK);
    }

    

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userServices.findByUserName(userName); 
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userServices.saveUser(userInDb); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping 
    public ResponseEntity<?> deleteUser(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
