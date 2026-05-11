package com.file.entity.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String jwt;
    private String title;
    private String message;
    private UserResponse response;
}
