package com.app.nisum.auth.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class LoginRequest {

    private String userName;
    private String password;
}
