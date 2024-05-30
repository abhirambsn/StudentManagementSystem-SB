package com.abhirambsn.studentmanagementsystem.dto;

import java.util.Date;

public record AdminResponseDto(
        String id,
        String first_name,
        String last_name,
        String email,
        String phone_number,
        String gender,
        String designation,
        Date date_of_birth
) {
}
