package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.CourseDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyResponseDto;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.models.Faculty;
import com.abhirambsn.studentmanagementsystem.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addNewCourse(
            @RequestBody CourseDto courseDto
    ) {
        return ResponseEntity.ok(courseService.addNewCourse(courseDto));
    }

    @GetMapping("/courses/{departmentId}")
    public ResponseEntity<List<Course>> getCoursesByDepartment(
            @PathVariable String departmentId
    ) {
        return ResponseEntity.ok(courseService.getCoursesByDepartment(departmentId));
    }

    @GetMapping("/course/{courseId}/faculty")
    public ResponseEntity<List<FacultyResponseDto>> getFacultyOfCourse(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(courseService.getFacultyOfCourse(courseId));
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteCourse(
            @PathVariable String courseId
    ) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/course/{courseId}/deactivate")
    public ResponseEntity<Boolean> deactivateCourse(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(courseService.deactivateCourse(courseId));
    }

    @PutMapping("/course/{courseId}/activate")
    public ResponseEntity<Boolean> activateCourse(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(courseService.reactivateCourse(courseId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourse(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }
}
