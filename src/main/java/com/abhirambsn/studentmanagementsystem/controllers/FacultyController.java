package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.FacultyDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyResponseDto;
import com.abhirambsn.studentmanagementsystem.services.FacultyService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/faculty")
    public FacultyResponseDto registerFaculty(
            @RequestBody FacultyDto facultyDto
            ) {
        return facultyService.registerFaculty(facultyDto);
    }

    @GetMapping("/faculty/{faculty_id}")
    public FacultyResponseDto getFaculty(
            @PathVariable String faculty_id
    ) {
        return facultyService.getFaculty(faculty_id);
    }

    @PutMapping("/faculty/{faculty_id}/course/{course_code}")
    public boolean assignCourseToFaculty(
            @PathVariable String course_code,
            @PathVariable String faculty_id
    ) {
        return facultyService.assignCourseToFaculty(course_code, faculty_id);
    }
}
