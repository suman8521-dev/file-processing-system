package com.file.service;

import com.file.entity.User;
import com.file.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class Userservices {
    private  final UserRepo userRepo;

    public Userservices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByEmail(String emil) {
        return userRepo.findByEmail(emil)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
