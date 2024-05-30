package com.abhirambsn.studentmanagementsystem.dto;

import java.util.Date;

public record AdminDto(
        String first_name,
        String last_name,
        String email,
        String phone_number,
        String designation,
        String gender,
        Date date_of_birth
) {
}
