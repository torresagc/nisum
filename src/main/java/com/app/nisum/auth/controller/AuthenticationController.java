package com.app.nisum.auth.controller;

import com.app.nisum.auth.core.dto.LoginRequest;
import com.app.nisum.auth.core.dto.LoginResponse;
import com.app.nisum.auth.core.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.nisum.common.route.Route;

@Tag(name = "Login", description = "")
@RequestMapping(Route.AUTH)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(Route.Security.LOGIN)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {

        LoginResponse loginResponse =  LoginResponse.builder()
                .token(authenticationService.authenticateToken(loginUserDto))
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
