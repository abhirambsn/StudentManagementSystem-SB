package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.CourseDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyAssignmentDto;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.models.Faculty;
import com.abhirambsn.studentmanagementsystem.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public Course addNewCourse(
            @RequestBody CourseDto courseDto
    ) {
        return courseService.addNewCourse(courseDto);
    }

    @GetMapping("/courses/{departmentId}")
    public List<Course> getCoursesByDepartment(
            @PathVariable String departmentId
    ) {
        return courseService.getCoursesByDepartment(departmentId);
    }

    @GetMapping("/course/{courseId}/faculty")
    public List<Faculty> getFacultyOfCourse(
            @PathVariable String courseId
    ) {
        return courseService.getFacultyOfCourse(courseId);
    }

    @DeleteMapping("/course/{courseId}")
    public void deleteCourse(
            @PathVariable String courseId
    ) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/course/{courseId}/deactivate")
    public boolean deactivateCourse(
            @PathVariable String courseId
    ) {
        return courseService.deactivateCourse(courseId);
    }

    @PutMapping("/course/{courseId}/activate")
    public boolean activateCourse(
            @PathVariable String courseId
    ) {
        return courseService.reactivateCourse(courseId);
    }

    @GetMapping("/course/{courseId}")
    public Course getCourse(
            @PathVariable String courseId
    ) {
        return courseService.getCourse(courseId);
    }
}
