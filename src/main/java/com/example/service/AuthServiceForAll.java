package com.example.service;

import com.example.DTO.LoginResponseDTO;
import com.example.DTO.RegisterRequestDTO;
import com.example.Entity.User;
import com.example.JWT.JWTService;
import com.example.Repository.UserRepository;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthServiceForAll { 
  @Autowired
   private UserRepository userRepository;
  
  @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;


    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already exists with username: " + registerRequestDTO.getUsername());
        }
        HashSet<String> roles =new HashSet<>();
        roles.add("ROLE_USER");
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        user.setEmail(registerRequestDTO.getEmail());
        return  userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already exists with username: " + registerRequestDTO.getUsername());
        }
        HashSet<String> roles =new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        user.setEmail(registerRequestDTO.getEmail());
        return  userRepository.save(user);
    }

    public LoginResponseDTO login(LoginResponseDTO loginResponseDTO) {
        User user = userRepository.findByUsername(loginResponseDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + loginResponseDTO.getUsername()));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginResponseDTO.getUsername(),
                        loginResponseDTO.getPassword()
                )
        );
        String token=jwtService.generateToken(user);
        return LoginResponseDTO.builder().jwtToken(token).username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }


}
