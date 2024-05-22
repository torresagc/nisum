package com.app.nisum.users.core.mapper;

import com.app.nisum.users.core.model.PhoneModel;
import com.app.nisum.users.domain.model.Phone;
import com.app.nisum.users.domain.model.User;

import java.util.UUID;

public class PhoneMapper {

    public static Phone toDto(PhoneModel request,UUID userId){
       return Phone.builder()
                .id(UUID.randomUUID())
                .countryCode(request.getCountryCode())
                .cityCode(request.getCityCode())
                .number(request.getNumber())
                .user(User.builder().id(userId).build())
                .build();
    }
}
