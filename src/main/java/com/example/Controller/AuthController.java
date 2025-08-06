package com.example.Controller;

import com.example.DTO.LoginResponseDTO;
import com.example.DTO.RegisterRequestDTO;
import com.example.Entity.User;
import com.example.service.AuthServiceForAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServiceForAll authServiceForAll;


    @PostMapping("/registerNormalUser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authServiceForAll.registerNormalUser(registerRequestDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginResponseDTO loginResponseDTO) {
        return ResponseEntity.ok(authServiceForAll.login(loginResponseDTO));
    }
}
