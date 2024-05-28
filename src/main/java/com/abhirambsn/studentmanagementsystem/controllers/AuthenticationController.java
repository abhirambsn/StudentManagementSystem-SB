package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.AuthResponseDto;
import com.abhirambsn.studentmanagementsystem.dto.RefreshTokenDto;
import com.abhirambsn.studentmanagementsystem.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/tokens")
    public ResponseEntity<AuthResponseDto> authenticate(
            @RequestHeader(value = "Authorization", required = true) String authHeader
    ) {
        String payload = authHeader.substring(5);
        String[] credentials = new String(
                Base64.getDecoder().decode(payload.trim())
        ).split(":");

        return ResponseEntity.ok(authenticationService.authenticate(credentials[0], credentials[1]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(
            @RequestBody RefreshTokenDto refreshTokenDto
    ) {
        return ResponseEntity.ok(authenticationService.refresh(refreshTokenDto));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.logout(request));
    }
}
