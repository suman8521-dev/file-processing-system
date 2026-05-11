package com.file.entity.req;
import com.file.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message ="full name is required..!!" )
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
}
