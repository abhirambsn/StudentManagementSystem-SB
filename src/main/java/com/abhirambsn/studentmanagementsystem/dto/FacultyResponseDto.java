package com.abhirambsn.studentmanagementsystem.dto;

import java.sql.Date;
import java.util.List;

public record FacultyResponseDto(
        String employee_id,
        String first_name,
        String last_name,
        String gender,
        String email,
        String phone_number,
        Date date_of_birth,
        Date date_of_joining,
        String department_name,
        List<String> courses
) {
}
