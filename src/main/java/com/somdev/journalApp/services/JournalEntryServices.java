package com.somdev.journalApp.services;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.somdev.journalApp.entity.JournalEntry;
import com.somdev.journalApp.repository.JournalEntryRepository;


@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry() {
        try {
            return journalEntryRepository.findAll();
        } catch (DataAccessException e) {
            // Log the exception or handle it as per your application's requirements
            e.printStackTrace(); // Example of logging the exception
            // You can throw a custom exception or return an empty list depending on your use case
            return Collections.emptyList();
        }
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
