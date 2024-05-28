package com.abhirambsn.studentmanagementsystem.dto;

public record StudentDto(
        String first_name,
        String last_name,
        String gender,
        String email,
        String phone_number,
        String program_code,
        String branch_id,
        int year_of_joining,
        String line1,
        String line2,
        String city,
        String state,
        String district,
        String country,
        int postal_code,
        boolean has_hostel
) {
}
