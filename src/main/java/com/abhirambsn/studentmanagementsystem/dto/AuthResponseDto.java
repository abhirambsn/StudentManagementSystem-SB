package com.abhirambsn.studentmanagementsystem.dto;

public record AuthResponseDto(
        String token,
        String refreshToken
) {
}
