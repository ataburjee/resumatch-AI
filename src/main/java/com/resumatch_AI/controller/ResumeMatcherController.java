package com.resumatch_AI.controller;

import com.resumatch_AI.model.dto.ResumeMatchResult;
import com.resumatch_AI.service.ResumeMatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class ResumeMatcherController {

    private final ResumeMatcherService matcherService;

    @PostMapping
    public ResponseEntity<List<ResumeMatchResult>> matchResumes(@RequestBody String jobDescription) {
        List<ResumeMatchResult> matches = matcherService.matchResumes(jobDescription);
        return ResponseEntity.ok(matches);
    }
}

