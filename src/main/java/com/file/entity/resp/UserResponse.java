package com.file.entity.resp;

import com.file.entity.UserRole;
import com.file.entity.UserStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {

    private  String  id;
    private String fullName;
    private String emil;
    private String phone;
    private UserRole role;
    private  String profileImage;
    private UserStatus status;
    private LocalDateTime createdAt;
    private Boolean verified;
    private LocalDateTime lastLogin;
}
