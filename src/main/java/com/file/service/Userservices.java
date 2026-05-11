package com.file.service;
import com.file.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class Userservices {
    private  final UserRepo userRepo;

    public Userservices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
