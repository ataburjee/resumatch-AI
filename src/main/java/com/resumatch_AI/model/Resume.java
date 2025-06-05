package com.resumatch_AI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "resumes")
public class Resume {
    @Id
    private String id;

    private String candidateName;
    private String email;
    private String phone;
    private String extractedSkills;
    private String format;

    @Lob
    private String rawText;

    @ManyToOne
    private User uploadedBy;

    private LocalDateTime uploadedAt;
}

