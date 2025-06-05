package com.resumatch_AI.model.dto;

import com.resumatch_AI.model.Role;
import com.resumatch_AI.model.User;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {
    private String token;
    private String id;
    private String email;
    private List<Role> roles;
    public AuthResponse(String token, User user) {
        this.token = token;
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

}
