package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.StudentDto;
import com.abhirambsn.studentmanagementsystem.dto.StudentResponseDto;
import com.abhirambsn.studentmanagementsystem.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> registerStudent(
            @RequestBody StudentDto studentDto
    ) {
        return ResponseEntity.ok(studentService.registerStudent(studentDto));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(defaultValue = "0", required = false) Integer offset
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(offset, limit));
    }

    @GetMapping("/students/{criteria}/{value}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByCriteria(
            @PathVariable String criteria,
            @PathVariable String value,
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(defaultValue = "0", required = false) Integer offset
    ) {
        if (criteria.equals("branch") || criteria.equals("program")) {
            if (criteria.equals("branch"))
                return ResponseEntity.ok(studentService.getStudentsByBranchId(value, offset, limit));
            else
                return ResponseEntity.ok(studentService.getStudentsByProgramCode(value, offset, limit));
        } else {
            throw new IllegalArgumentException("Invalid criteria");
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentResponseDto>getStudent(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping("/student/{enrolment_no}/register/{course_code}")
    public ResponseEntity<Boolean> registerCourse(
            @PathVariable String enrolment_no,
            @PathVariable String course_code
    ) {
        return ResponseEntity.ok(studentService.registerCourse(enrolment_no, course_code));
    }

    @PutMapping("/student/{enrolment_no}/deactivate")
    public ResponseEntity<Boolean> deactivateStudent(
            @PathVariable String enrolment_no
    ) {
        return ResponseEntity.ok(studentService.deactivateStudent(enrolment_no));
    }

    @PutMapping("/student/{enrolment_no}/activate")
    public ResponseEntity<Boolean> activateStudent(
            @PathVariable String enrolment_no
    ) {
        return ResponseEntity.ok(studentService.reactivateStudent(enrolment_no));
    }
}
