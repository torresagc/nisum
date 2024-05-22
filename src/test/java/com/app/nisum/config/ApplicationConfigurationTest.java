package com.app.nisum.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class ApplicationConfigurationTest {

    @Bean
    PasswordEncoder passwordEncoderTest() {
        return NoOpPasswordEncoder.getInstance();
    }
}
