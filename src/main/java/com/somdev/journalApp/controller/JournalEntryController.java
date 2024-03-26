package com.somdev.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import com.somdev.journalApp.entity.JournalEntry;
import com.somdev.journalApp.services.JournalEntryServices;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll() {
        return journalEntryServices.getAllEntry();
    }

    @GetMapping("id/{id}")
    public Optional<JournalEntry> getById(@PathVariable ObjectId id){
        return journalEntryServices.getById(id);
    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryServices.saveEntry(myEntry);
        return "Data Inserted";
    }

    @PutMapping("id/{id}")
    public JournalEntry editEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        return null;
    }

    @DeleteMapping("id/{id}")
    public String deleteEntryById(@PathVariable ObjectId id){
        journalEntryServices.deleteById(id);
        return "item deleted";
    }

}