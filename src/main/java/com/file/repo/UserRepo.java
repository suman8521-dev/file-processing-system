package com.file.repo;
import com.file.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {

    // Find by email
    Optional<User> findByEmail(String email);
}
