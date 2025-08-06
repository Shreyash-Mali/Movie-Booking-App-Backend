package com.example.DTO;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
