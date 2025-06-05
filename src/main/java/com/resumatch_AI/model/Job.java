package com.resumatch_AI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    private String id;

    private String title;

    @Column(length = 5000)
    private String description;

    private LocalDateTime postedAt;

    @ManyToOne
    private User postedBy;
}


