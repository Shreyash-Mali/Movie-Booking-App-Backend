package com.example.Controller;

import com.example.DTO.RegisterRequestDTO;
import com.example.Entity.User;
import com.example.service.AuthServiceForAll;
import com.example.service.AuthServiceForAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AuthServiceForAll authServiceForAll;

    @PostMapping("/registerAdminUser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authServiceForAll.registerAdminUser(registerRequestDTO));
    }
}
