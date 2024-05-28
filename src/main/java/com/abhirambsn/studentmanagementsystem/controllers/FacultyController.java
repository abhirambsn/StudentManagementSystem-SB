package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.FacultyDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyResponseDto;
import com.abhirambsn.studentmanagementsystem.services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/faculty")
    public ResponseEntity<FacultyResponseDto> registerFaculty(
            @RequestBody FacultyDto facultyDto
            ) {
        return ResponseEntity.ok(facultyService.registerFaculty(facultyDto));
    }

    @GetMapping("/faculty/{faculty_id}")
    public ResponseEntity<FacultyResponseDto> getFaculty(
            @PathVariable String faculty_id
    ) {
        return ResponseEntity.ok(facultyService.getFaculty(faculty_id));
    }

    @PutMapping("/faculty/{faculty_id}/course/{course_code}")
    public ResponseEntity<Boolean> assignCourseToFaculty(
            @PathVariable String course_code,
            @PathVariable String faculty_id
    ) {
        return ResponseEntity.ok(facultyService.assignCourseToFaculty(course_code, faculty_id));
    }
}
