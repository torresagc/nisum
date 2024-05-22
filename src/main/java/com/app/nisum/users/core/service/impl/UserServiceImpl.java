package com.app.nisum.users.core.service.impl;

import com.app.nisum.auth.core.dto.LoginRequest;
import com.app.nisum.auth.core.service.AuthenticationService;
import com.app.nisum.common.exception.ErrorMessage;
import com.app.nisum.common.exception.NisumExeption;
import com.app.nisum.common.util.ValidationUtil;
import com.app.nisum.users.core.mapper.UserMapper;
import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.core.service.UserService;
import com.app.nisum.users.domain.model.User;
import com.app.nisum.users.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        AtomicReference<UserResponse> response = new AtomicReference<>();
        if(!ValidationUtil.isEmailValid(request.getEmail()))
            throw new NisumExeption(ErrorMessage.INVALID_EMAIL, HttpStatus.BAD_REQUEST);

        if(!ValidationUtil.isPasswordValid(request.getPassword()))
            throw new NisumExeption(ErrorMessage.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);


        userRepository.findByEmail(request.getEmail()).ifPresentOrElse(
                        (value)-> {throw new NisumExeption(ErrorMessage.EXIST_EMAIL, HttpStatus.BAD_REQUEST);},
                        ()->{
                            String passowrd = passwordEncoder.encode(passwordEncoder.encode(request.getPassword()));
                            User user = userRepository.save(UserMapper.toDto(request, passowrd));
                            String token = authenticationService.authenticateToken(LoginRequest.builder()
                                            .userName(request.getEmail())
                                            .password(request.getPassword())
                                    .build());
                            user.setToken(token);
                            userRepository.save(user);
                            response.set(UserMapper.toEntity(user));
                        }

                );
        if(response.get()!= null)
            return response.get();
       throw new NisumExeption(ErrorMessage.NOT_CHECK, HttpStatus.BAD_REQUEST);
    }
}
