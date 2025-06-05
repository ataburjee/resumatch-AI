package com.resumatch_AI.repository;

import com.resumatch_AI.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, String> {
}
