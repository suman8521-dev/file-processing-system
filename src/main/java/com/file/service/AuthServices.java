package com.file.service;

import com.file.entity.req.LoginRequest;
import com.file.entity.req.SignupRequest;
import com.file.entity.resp.AuthResponse;

public interface AuthServices {

    AuthResponse signup(SignupRequest req);
    AuthResponse login(LoginRequest loginRequest);
}
