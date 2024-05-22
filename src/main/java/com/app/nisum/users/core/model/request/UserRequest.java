package com.app.nisum.users.core.model.request;

import com.app.nisum.users.core.model.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;
}
