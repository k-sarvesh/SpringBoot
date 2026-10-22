package com.Sarvesh.JournalApp.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.Sarvesh.JournalApp.Entity.JournalEntry;
import com.Sarvesh.JournalApp.Entity.User;
import com.Sarvesh.JournalApp.Repository.JournalEntryRepository;



@Component
public class JournalEntryServies {

    @Autowired // Injecting Journal Entry
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserServices userServices;

    @Transactional 
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userServices.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry); 
        user.getJournalEntries().add(saved);
        userServices.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry); 
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}

//Controller -> Service -> Repository -> DataBase