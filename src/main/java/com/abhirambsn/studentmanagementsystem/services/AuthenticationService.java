package com.abhirambsn.studentmanagementsystem.services;

import com.abhirambsn.studentmanagementsystem.config.JwtService;
import com.abhirambsn.studentmanagementsystem.dto.AuthResponseDto;
import com.abhirambsn.studentmanagementsystem.dto.RefreshTokenDto;
import com.abhirambsn.studentmanagementsystem.models.RefreshToken;
import com.abhirambsn.studentmanagementsystem.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final InMemoryTokenBlacklist inMemoryTokenBlacklist;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService, RefreshTokenService refreshTokenService, InMemoryTokenBlacklist inMemoryTokenBlacklist) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.inMemoryTokenBlacklist = inMemoryTokenBlacklist;
    }

    public AuthResponseDto authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var user = userRepository.findById(username).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        var jwtToken = jwtService.generateToken(null, user);

        RefreshToken token = refreshTokenService.buildRefreshToken(user.getUsername());
        return new AuthResponseDto(jwtToken, token.getToken());
    }

    public AuthResponseDto refresh(RefreshTokenDto refreshTokenDto) {
        return refreshTokenService.findByToken(refreshTokenDto.refresh_token())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String access_token = jwtService.generateToken(null, user);
                    return new AuthResponseDto(
                            access_token,
                            refreshTokenDto.refresh_token()
                    );
                }).orElseThrow(() -> new RuntimeException("Refresh token not in DB"));
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public boolean logout(HttpServletRequest request) {
        String token = this.extractTokenFromRequest(request);
        inMemoryTokenBlacklist.addToBlacklist(token);

        return true;
    }
}
