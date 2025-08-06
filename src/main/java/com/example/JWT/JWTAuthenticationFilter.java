package com.example.JWT;

import com.example.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;
        if(authHeader==null || authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //Extract the JWT token from the header
        jwtToken = authHeader.substring(7);
        //Extract the username from the JWT token
        username = jwtService.extractUsername(jwtToken);
        //If the username is not null and the user is not authenticated, set the authentication in the security context
        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            //Load the user details from the database
            var userDetails = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
            //If the JWT token is valid, set the authentication in the security context
            if(jwtService.isTokenValid(jwtToken, userDetails)){
                List<SimpleGrantedAuthority> authorities = userDetails.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .toList();
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
                //set authentication details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //update the security context with the authentication token
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
