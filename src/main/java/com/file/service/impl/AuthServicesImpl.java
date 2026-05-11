package com.file.service.impl;
import com.file.entity.User;
import com.file.entity.UserRole;
import com.file.entity.req.LoginRequest;
import com.file.entity.req.SignupRequest;
import com.file.entity.resp.AuthResponse;
import com.file.mapper.UserMapper;
import com.file.repo.UserRepo;
import com.file.service.AuthServices;
import com.file.service.Userservices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServicesImpl implements AuthServices {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Override
    public AuthResponse signup(final SignupRequest req) {
        Optional<User> existingUser = userRepo.findByEmail(req.getEmail());

        if (existingUser != null) {
            throw new RuntimeException("Email already registered");
        }

        if (req.getRole()==UserRole.ROLE_ADMIN){
            throw new RuntimeException("can't Regester as a admin role..!!");
        }
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .fullName(req.getFullname())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .build();
        User savedUser=userRepo.save(user);

        return AuthResponse.builder()
                .title("Welcome "+savedUser.getFullName() )
                .message("Signup successful")
                .jwt("jwt")
                .response(UserMapper.toDto(savedUser))
                .build();
    }

    @Override
    public AuthResponse login(final LoginRequest loginRequest) {
        return null;
    }


}
