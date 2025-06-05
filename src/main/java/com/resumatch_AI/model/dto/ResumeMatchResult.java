package com.resumatch_AI.model.dto;

import com.resumatch_AI.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeMatchResult {
    private Resume resume;
    private double score; // Range: 0.0 to 1.0
}
