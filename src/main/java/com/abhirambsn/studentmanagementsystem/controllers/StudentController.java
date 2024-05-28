package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.StudentDto;
import com.abhirambsn.studentmanagementsystem.dto.StudentResponseDto;
import com.abhirambsn.studentmanagementsystem.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto registerStudent(
            @RequestBody StudentDto studentDto
    ) {
        return studentService.registerStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(defaultValue = "0", required = false) Integer offset
    ) {
        return studentService.getAllStudents(offset, limit);
    }

    @GetMapping("/students/{criteria}/{value}")
    public List<StudentResponseDto> getStudentsByCriteria(
            @PathVariable String criteria,
            @PathVariable String value,
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(defaultValue = "0", required = false) Integer offset
    ) {
        if (criteria.equals("branch") || criteria.equals("program")) {
            if (criteria.equals("branch"))
                return studentService.getStudentsByBranchId(value, offset, limit);
            else
                return studentService.getStudentsByProgramCode(value, offset, limit);
        } else {
            throw new IllegalArgumentException("Invalid criteria");
        }
    }

    @GetMapping("/student/{id}")
    public StudentResponseDto getStudent(
            @PathVariable String id
    ) {
        return studentService.getStudent(id);
    }

    @PutMapping("/student/{enrolment_no}/register/{course_code}")
    public boolean registerCourse(
            @PathVariable String enrolment_no,
            @PathVariable String course_code
    ) {
        return studentService.registerCourse(enrolment_no, course_code);
    }

    @PutMapping("/student/{enrolment_no}/deactivate")
    public boolean deactivateStudent(
            @PathVariable String enrolment_no
    ) {
        return studentService.deactivateStudent(enrolment_no);
    }

    @PutMapping("/student/{enrolment_no}/activate")
    public boolean activateStudent(
            @PathVariable String enrolment_no
    ) {
        return studentService.reactivateStudent(enrolment_no);
    }
}
