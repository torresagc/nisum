package com.app.nisum.users.controller;

import com.app.nisum.common.route.Route;
import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "")
@RequestMapping(Route.AUTH)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(Route.User.CREATE_USER)
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}
