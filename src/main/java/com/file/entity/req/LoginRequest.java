package com.file.entity.req;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
