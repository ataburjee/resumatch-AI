package com.resumatch_AI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
    @GetMapping
    public String recruiterAccess() {
        return "Hello Recruiter 💼";
    }
}
