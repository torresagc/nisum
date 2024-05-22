package com.app.nisum.users.mapper;

import com.app.nisum.auth.domain.model.Credential;
import com.app.nisum.config.ApplicationConfigurationTest;
import com.app.nisum.users.core.mapper.UserMapper;
import com.app.nisum.users.core.model.PhoneModel;
import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;
import com.app.nisum.users.domain.model.Phone;
import com.app.nisum.users.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Import(ApplicationConfigurationTest.class)
@SpringBootTest
public class UserMapperTest {
    @MockBean
    @Qualifier("PasswordEncoderTest")
    private PasswordEncoder passwordEncoder;

    @Test
    public void toDtoValid(){
        PhoneModel phoneModel = PhoneModel.builder()
                .countryCode("57")
                .number("3004976567")
                .cityCode("13")
                .build();
        UserRequest modelRequest = UserRequest.builder()
                .email("pedro@gmail.com")
                .name("pedro")
                .password("Cartagena1!")
                .phones(List.of(phoneModel))
                .build();
        User user = UserMapper.toDto(modelRequest, "passwordEncoder");
        Assertions.assertEquals(user.getEmail(),modelRequest.getEmail());
        Assertions.assertEquals(user.getName(),modelRequest.getName());
        Assertions.assertNotNull(user.getCredential());
        Assertions.assertNotNull(user.getPhoneList());
    }

    @Test
    public void toEntityValid(){
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
                .token("tokenuuid")
                .credential(credentialEntity)
                .phoneList(List.of(phoneEntity))
                .build();

        UserResponse response = UserMapper.toEntity(entity);
        Assertions.assertEquals(response.getId(),entity.getId().toString());
        Assertions.assertEquals(response.getToken(),entity.getToken());
    }

}
