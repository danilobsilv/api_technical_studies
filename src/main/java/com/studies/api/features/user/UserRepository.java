package com.studies.api.features.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUserEmail(String email);
    boolean existsByUserEmailAndUserIdNot(String email, String userId);
    boolean existsByUserEmail(String email);

    @Query("""
            SELECT m FROM User m
            WHERE m.isActive = true
            ORDER BY m.userName ASC
            """)
    Page<User> findAllByActiveTrue(Pageable pageable);
}
