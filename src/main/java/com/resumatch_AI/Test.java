package com.resumatch_AI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class Test {

    @GetMapping
    public String testApi() {
        return "Backend is running! 🚀";
    }
}

