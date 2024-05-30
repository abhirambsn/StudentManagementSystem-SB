package com.abhirambsn.studentmanagementsystem.dto;

import java.sql.Date;

public record FacultyDto(
        String first_name,
        String last_name,
        String gender,
        String email,
        String phone_number,
        Date date_of_birth,
        Date date_of_joining,
        String department_id,
        String designation
) {
}
