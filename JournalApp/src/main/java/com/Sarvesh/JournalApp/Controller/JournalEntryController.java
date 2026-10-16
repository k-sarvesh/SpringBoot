package com.Sarvesh.JournalApp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Sarvesh.JournalApp.Entity.JournalEntry;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private HashMap<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    // @PostMapping
    // public boolean addEntries(@RequestBody JournalEntry newEntry) {
    //     journalEntries.put(newEntry.getId(), newEntry);
    //     return true;
    // }

    @GetMapping("id/{MyId}")
    public JournalEntry getjournalEntryByid(@PathVariable String MyId) {
        return journalEntries.get(MyId);
    }

    @DeleteMapping("/id/{MyId}")
    public boolean deleteEntry(@PathVariable String MyId) {
        journalEntries.remove(MyId);
        return true;
    }

    @PutMapping("/id/{MyId}")
    public JournalEntry updateEntry(@PathVariable String id, @RequestBody JournalEntry newEntry) {
        journalEntries.put(id, newEntry);
        return newEntry;
    }
}
