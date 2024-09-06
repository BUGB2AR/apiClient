package com.providertechapi.cliente.web;

import com.providertechapi.cliente.core.request.AuthRequest;
import com.providertechapi.cliente.infra.security.InMemoryUserDetailsService;
import com.providertechapi.cliente.infra.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthWeb {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final InMemoryUserDetailsService userDetailsService;

    public AuthWeb(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, InMemoryUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), authRequest.getPassword(), userDetails.getAuthorities())
            );

            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas", e);
        }
    }

}
