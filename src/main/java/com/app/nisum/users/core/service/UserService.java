package com.app.nisum.users.core.service;

import com.app.nisum.users.core.model.request.UserRequest;
import com.app.nisum.users.core.model.response.UserResponse;

public interface UserService {
  UserResponse createUser(UserRequest request);
}
