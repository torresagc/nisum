package com.app.nisum.users.service;

import com.app.nisum.auth.core.service.AuthenticationService;
import com.app.nisum.auth.domain.model.Credential;
import com.app.nisum.common.exception.ErrorMessage;
import com.app.nisum.common.exception.NisumExeption;
import com.app.nisum.config.ApplicationConfigurationTest;
import com.app.nisum.users.core.model.PhoneModel;
import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.core.service.impl.UserServiceImpl;
import com.app.nisum.users.domain.model.Phone;
import com.app.nisum.users.domain.model.User;
import com.app.nisum.users.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Import(ApplicationConfigurationTest.class)
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Qualifier("PasswordEncoderTest")
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void createUserInvalidEmail(){
        UserRequest request = UserRequest.builder()
                .email("peorp!")
                .build();
        NisumExeption exception = Assertions.assertThrows(NisumExeption.class, () -> userService.createUser(request));
        Assertions.assertEquals(exception.getDetail().getMessage(), ErrorMessage.INVALID_EMAIL);
        Assertions.assertEquals(exception.getCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createUserInvalidPassword(){
        UserRequest request = UserRequest.builder()
                .password("peorp!")
                .email("pedro@gmail.com")
                .build();
        NisumExeption exception = Assertions.assertThrows(NisumExeption.class, () -> userService.createUser(request));
        Assertions.assertEquals(exception.getDetail().getMessage(), ErrorMessage.INVALID_PASSWORD);
        Assertions.assertEquals(exception.getCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createUserExistEmail(){
        UserRequest request = UserRequest.builder()
                .password("Caratgena1!")
                .email("pedro@gmail.com")
                .build();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
        NisumExeption exception = Assertions.assertThrows(NisumExeption.class, () -> userService.createUser(request));
        Assertions.assertEquals(exception.getDetail().getMessage(), ErrorMessage.EXIST_EMAIL);
        Assertions.assertEquals(exception.getCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createUserValid(){
        PhoneModel phoneModel = PhoneModel.builder()
                .countryCode("57")
                .number("3004976567")
                .cityCode("13")
                .build();
        UserRequest request = UserRequest.builder()
                .email("pedro@gmail.com")
                .name("pedro")
                .password("Cartagena1!")
                .phones(List.of(phoneModel))
                .build();

        Phone phoneEntity = Phone.builder()
                .countryCode("57")
                .number("3004976567")
                .cityCode("13")
                .id(UUID.randomUUID())
                .build();
        Credential credentialEntity = Credential.builder()
                .userName("pedro@gmial.com")
                .password(passwordEncoder.encode("1234"))
                .id(UUID.randomUUID())
                .build();
        User entity = User.builder()
                .id(UUID.randomUUID())
                .isActive(true)
                .email("pedro@gmial.com")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .credential(credentialEntity)
                .phoneList(List.of(phoneEntity))
                .token("TEXTEXT")
                .build();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.ofNullable(null));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(entity);
        Mockito.when(authenticationService.authenticateToken(Mockito.any())).thenReturn("TEXTEXT");
        Mockito.when(passwordEncoder.encode(Mockito.any())).thenReturn("TEXTEXT");

        UserResponse response = userService.createUser(request);
        Assertions.assertEquals(response.getToken(), entity.getToken());
        Assertions.assertEquals(response.getId(), entity.getId().toString());
    }
}
