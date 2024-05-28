package com.abhirambsn.studentmanagementsystem.dto;

import com.abhirambsn.studentmanagementsystem.models.CourseAssignmentType;
import com.abhirambsn.studentmanagementsystem.models.CourseType;

import java.sql.Date;

public record CourseDto(
        String course_name,
        String department_id,
        String description_link,
        String program_id,
        int year_of_start,
        CourseType course_type,
        CourseAssignmentType course_assignment_type
) {
}
