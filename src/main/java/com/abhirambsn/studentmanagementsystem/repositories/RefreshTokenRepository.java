package com.abhirambsn.studentmanagementsystem.repositories;

import com.abhirambsn.studentmanagementsystem.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Query("SELECT r FROM RefreshToken r WHERE r.user.username = :username")
    Optional<RefreshToken> findByUsername(String username);

}
