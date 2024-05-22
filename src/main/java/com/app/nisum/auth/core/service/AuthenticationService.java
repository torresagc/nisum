package com.app.nisum.auth.core.service;

import com.app.nisum.auth.core.dto.LoginRequest;
import com.app.nisum.auth.domain.model.Credential;
import com.app.nisum.auth.domain.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public Credential authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

        return credentialRepository.findByUserName(input.getUserName())
                .orElseThrow();
    }

    public String authenticateToken(LoginRequest request){
        Credential authenticatedUser = this.authenticate(request);
        return jwtService.generateToken(authenticatedUser);
    }
}
