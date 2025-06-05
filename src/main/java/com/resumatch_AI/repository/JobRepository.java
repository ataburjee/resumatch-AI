package com.resumatch_AI.repository;

import com.resumatch_AI.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {

}