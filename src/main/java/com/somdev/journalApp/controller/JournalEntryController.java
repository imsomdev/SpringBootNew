package com.somdev.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import com.somdev.journalApp.entity.JournalEntry;
import com.somdev.journalApp.services.JournalEntryServices;
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


@RestController
@RequestMapping("api/journal")
public class JournalEntryController {
    
    @Autowired
    JournalEntryServices journalEntryServices;
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> journalEntries = journalEntryServices.getAllEntry();
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryServices.getById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("details", "Journal Entry with ID " + id + " not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryServices.saveEntry(myEntry);
            Map<String, Object> response = new HashMap<>();
            response.put("details", "Data Inserted");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST); 
        }
        
    }

    // @PutMapping("id/{id}")
    // public JournalEntry editEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
    //     return null;
    // }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id){
        journalEntryServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}