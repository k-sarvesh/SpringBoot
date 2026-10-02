package com.Sarvesh.JournalApp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Sarvesh.JournalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/journal")
public class JournalEntryController {
 
    private HashMap<Long , JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean addEntries(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId() , newEntry);
        return true;
    }

    @GetMapping("id/{MyId}")
    public JournalEntry getjournalEntryByid(@PathVariable Long MyId){
        return journalEntries.get(MyId);
    }
}
