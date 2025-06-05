package com.resumatch_AI.controller;

import com.resumatch_AI.model.User;
import com.resumatch_AI.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file,
                                               Authentication authentication) {
        String email = ((User)authentication.getPrincipal()).getEmail();
        resumeService.saveParsedResume(file, email);
        return ResponseEntity.ok("✅ Resume uploaded and parsed successfully");
    }
}

