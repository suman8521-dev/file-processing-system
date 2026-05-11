package com.file.repo;
import com.file.entity.User;
import com.file.entity.UserRole;
import com.file.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {

    // Find by email
    Optional<User> findByEmail(String emil);

    // Check email exists
    boolean existsByEmil(String emil);

    // Find by phone
    Optional<User> findByPhone(String phone);

    // Find by role
    List<User> findByRole(UserRole role);

    // Find by status
    List<User> findByStatus(UserStatus status);

    // Find active users
    List<User> findByStatusAndDeletedAtIsNull(UserStatus status);

    // Find by full name
    List<User> findByFullNameContainingIgnoreCase(String fullName);

    // Find non-deleted users
    List<User> findByDeletedAtIsNull();

    // Find suspended users
    List<User> findBySuspendedAtIsNotNull();
    // Login query
    Optional<User> findByEmilAndDeletedAtIsNull(String emil);
}
