package com.example.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponseDTO {
    private String jwtToken;
    private String username;
    private String password;
    private Set<String> roles;
}
