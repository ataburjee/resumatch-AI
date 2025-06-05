package com.resumatch_AI.service;

import com.resumatch_AI.model.Resume;
import com.resumatch_AI.model.dto.ResumeMatchResult;
import com.resumatch_AI.repository.ResumeRepository;
import com.resumatch_AI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeMatcherService {

    private final ResumeRepository resumeRepository;
//    private final UserRepository userRepository;

    public List<ResumeMatchResult> matchResumes(String jobDescription) {
        String jobText = jobDescription.toLowerCase();

        List<Resume> resumes = resumeRepository.findAll();

        return resumes.stream()
                .map(resume -> {
                    String resumeText = resume.getRawText().toLowerCase();
                    double score = computeScore(jobText, resumeText);
                    return new ResumeMatchResult(resume, score);
                })
                .sorted(Comparator.comparingDouble(ResumeMatchResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    private double computeScore(String jobText, String resumeText) {
        Set<String> jobWords = tokenize(jobText);
        Set<String> resumeWords = tokenize(resumeText);

        if (jobWords.isEmpty() || resumeWords.isEmpty()) return 0.0;

        int intersection = 0;
        for (String word : jobWords) {
            if (resumeWords.contains(word)) intersection++;
        }

        double tf = (double) intersection / jobWords.size(); // term frequency
        double idf = Math.log(1 + (1.0 / (1 + intersection))); // inverse doc frequency-ish

        return tf * idf;
    }

    private static Set<String> tokenize(String text) {
        return Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(w -> w.length() > 2 && !isStopWord(w))
                .collect(Collectors.toSet());
    }

    private static boolean isStopWord(String word) {
        return List.of("the", "and", "are", "with", "for", "you", "your", "from", "this", "that").contains(word);
    }
}
