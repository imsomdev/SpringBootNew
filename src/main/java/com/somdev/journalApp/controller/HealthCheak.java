package com.somdev.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheak {
    @GetMapping("/")
    public String check(){
        return "API Working";
    }
}
