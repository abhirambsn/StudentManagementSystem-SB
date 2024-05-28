package com.abhirambsn.studentmanagementsystem.dto;

public record StudentResponseDto(
        String enrolment_number,
        String first_name,
        String last_name,
        String email,
        String phone_number,
        String program,
        String branch,
        int year_of_joining,
        String address,
        boolean has_hostel
) {
}
