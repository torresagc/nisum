package com.app.nisum.users.core.mapper;

import com.app.nisum.auth.domain.model.Credential;
import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.domain.model.User;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toDto(UserRequest request, String passwordEncoder){
        UUID userId = UUID.randomUUID();
        UUID credentialId = UUID.randomUUID();
       return User.builder()
                .id(userId)
                .email(request.getEmail())
                .name(request.getName())
                .isActive(true)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .phoneList(request.getPhones().stream().map(phone ->
                     PhoneMapper.toDto(phone, userId)
                ).collect(Collectors.toList()))
                .credential(Credential.builder()
                        .id(credentialId)
                        .userName(request.getEmail())
                        .password(passwordEncoder)
                        .build())
                .build();


    }

    public static UserResponse toEntity(User entity){
        return UserResponse.builder()
                .created(entity.getCreated())
                .token(entity.getToken())
                .modified(entity.getModified())
                .isActive(entity.getIsActive())
                .id(entity.getId().toString())
                .build();
    }
}
