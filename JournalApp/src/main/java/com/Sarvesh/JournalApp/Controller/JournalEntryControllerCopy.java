package com.Sarvesh.JournalApp.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.Sarvesh.JournalApp.Entity.JournalEntry;
import com.Sarvesh.JournalApp.Service.JournalEntryServies;
import com.Sarvesh.JournalApp.Service.UserServices;
import com.Sarvesh.JournalApp.Entity.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journalcopy")
public class JournalEntryControllerCopy {

    @Autowired
    private JournalEntryServies journalEntryServies;
    @Autowired
    private UserServices userServices;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllEntriesOfUser(@PathVariable String userName) {
        User user = userServices.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && all.size() > 0) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> addEntries(@RequestBody JournalEntry newEntry, @PathVariable String userName) {
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryServies.saveEntry(newEntry,userName);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{MyId}")
    public ResponseEntity<JournalEntry> getjournalEntryByid(@PathVariable ObjectId MyId) {
        Optional<JournalEntry> journalEntry = journalEntryServies.getById(MyId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{MyId}")
    public ResponseEntity<?> deleteEntry(@PathVariable String userName, @PathVariable ObjectId MyId) {
        User user = userServices.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(MyId));
        userServices.saveUser(user);
        journalEntryServies.deleteById(MyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{MyId}")
    public ResponseEntity<JournalEntry> updateEntry(
        @PathVariable("MyId") ObjectId MyId,
        @PathVariable("userName") String userName,
        @RequestBody JournalEntry newEntry) 
        {
        JournalEntry old = journalEntryServies.getById(MyId).orElse(null);
        if (old != null) {
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                    : old.getContent());
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                    : old.getTitle());
            journalEntryServies.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
