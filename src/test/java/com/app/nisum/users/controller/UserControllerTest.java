package com.app.nisum.users.controller;

import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.core.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private UserController userController;

    @Test
    public void createTest(){
        UserRequest request = UserRequest.builder()
                .password("Caratgena1!")
                .email("pedro@gmail.com")
                .build();
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(new UserResponse());
        ResponseEntity<UserResponse> response = userController.create(request);
        Assertions.assertNotNull(response);
    }
}
