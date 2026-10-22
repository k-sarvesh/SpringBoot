package com.Sarvesh.JournalApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.bson.types.ObjectId;

import com.Sarvesh.JournalApp.Entity.User;
import com.Sarvesh.JournalApp.Repository.UserRepository;


@Component 
public class UserServices {
    
    @Autowired 
    private UserRepository userRepository;


    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
       return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteByUserName(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    

}
