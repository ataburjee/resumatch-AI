package com.resumatch_AI.model.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
