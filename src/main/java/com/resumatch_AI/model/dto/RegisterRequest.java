package com.resumatch_AI.model.dto;

import com.resumatch_AI.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private List<Role> roles;
}
