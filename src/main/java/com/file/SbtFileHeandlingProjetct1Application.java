package com.file;

import com.file.entity.User;
import com.file.entity.req.SignupRequest;
import com.file.entity.resp.AuthResponse;
import com.file.service.AuthServices;
import com.file.service.Userservices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class SbtFileHeandlingProjetct1Application implements ApplicationRunner {

    private final AuthServices authServices;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SbtFileHeandlingProjetct1Application.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkUser();
    }

    public AuthResponse checkUser() {

        SignupRequest request = new SignupRequest();
        // set values
        request.setFullname("Test User");
        request.setEmail("test@gmail.com");
        request.setPassword("123456");
        return authServices.signup(request);
    }


}
