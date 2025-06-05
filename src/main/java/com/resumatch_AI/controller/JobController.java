package com.resumatch_AI.controller;

import com.resumatch_AI.model.Job;
import com.resumatch_AI.model.User;
import com.resumatch_AI.model.dto.ResumeMatchResult;
import com.resumatch_AI.repository.JobRepository;
import com.resumatch_AI.service.ResumeMatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;
    private final ResumeMatcherService matcherService;

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job, Authentication auth) {
        User user = (User) auth.getPrincipal();
        job.setId(UUID.randomUUID().toString());
        job.setPostedBy(user);
        job.setPostedAt(LocalDateTime.now());
        return ResponseEntity.ok(jobRepository.save(job));
    }

    @GetMapping("/{jobId}/matches")
    public ResponseEntity<List<ResumeMatchResult>> matchForJob(@PathVariable String jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("❌ Job not found"));

        List<ResumeMatchResult> matches = matcherService.matchResumes(job.getDescription());
        return ResponseEntity.ok(matches);
    }
}
