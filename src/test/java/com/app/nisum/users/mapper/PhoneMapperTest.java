package com.app.nisum.users.mapper;

import com.app.nisum.users.core.mapper.PhoneMapper;
import com.app.nisum.users.core.model.PhoneModel;
import com.app.nisum.users.domain.model.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class PhoneMapperTest {

    @Test
    public void toDtoValid(){
        PhoneModel model = PhoneModel.builder()
                .countryCode("57")
                .number("3004976567")
                .cityCode("13")
                .build();
        Phone phone = PhoneMapper.toDto(model, UUID.randomUUID());
        Assertions.assertEquals(model.getCityCode(),phone.getCityCode());
        Assertions.assertEquals(model.getNumber(),phone.getNumber());
        Assertions.assertEquals(model.getCountryCode(),phone.getCountryCode());
    }

}
