package com.resumatch_AI.service;

import com.resumatch_AI.model.Resume;
import com.resumatch_AI.model.User;
import com.resumatch_AI.repository.ResumeRepository;
import com.resumatch_AI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    private static final List<String> SKILL_KEYWORDS = List.of(
            "java", "spring", "hibernate", "docker", "kubernetes", "react", "node.js", "sql", "mongodb", "python", "aws"
    );

    public String extractText(MultipartFile file) {
        try (InputStream stream = file.getInputStream()) {
            Tika tika = new Tika();
            return tika.parseToString(stream);
        } catch (IOException | TikaException e) {
            throw new RuntimeException("Failed to extract text from file", e);
        }
    }

    public String extractSkills(String text) {
        String lowerText = text.toLowerCase();
        return SKILL_KEYWORDS.stream()
                .filter(lowerText::contains)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public void saveParsedResume(MultipartFile file, String userEmail) {
        String text = extractText(file);
        String skills = extractSkills(text);

        Resume resume = new Resume();
        resume.setId(UUID.randomUUID().toString());
        resume.setRawText(text);
        resume.setExtractedSkills(skills);
        resume.setUploadedAt(LocalDateTime.now());

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        resume.setUploadedBy(user);

        resumeRepository.save(resume);
    }
}
