package com.Sarvesh.JournalApp.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

import com.Sarvesh.JournalApp.Entity.JournalEntry;
import com.Sarvesh.JournalApp.Repository.JournalEntryRepository;

@Component
public class JournalEntryServies {

    @Autowired // Injecting Journal Entry
    private JournalEntryRepository journalEntryRepository;

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