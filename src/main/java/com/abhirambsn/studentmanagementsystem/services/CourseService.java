package com.abhirambsn.studentmanagementsystem.services;

import com.abhirambsn.studentmanagementsystem.dto.CourseDto;
import com.abhirambsn.studentmanagementsystem.mappers.CourseMapper;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.models.Department;
import com.abhirambsn.studentmanagementsystem.models.Faculty;
import com.abhirambsn.studentmanagementsystem.repositories.CourseRepository;
import com.abhirambsn.studentmanagementsystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.courseMapper = courseMapper;
    }

    public boolean deactivateCourse(String course_code) {
        try {
            Course course = courseRepository.findById(course_code).orElseThrow();
            course.setActive(false);
            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            System.err.println("Error Occurred while Deactivating Course:" + e);
            return false;
        }
    }

    public boolean reactivateCourse(String course_code) {
        try {
            Course course = courseRepository.findById(course_code).orElseThrow();
            course.setActive(true);
            courseRepository.save(course);
            return true;
        } catch (Exception e) {
            System.err.println("Error Occurred while Reactivating Course:" + e);
            return false;
        }
    }

    public Course addNewCourse(CourseDto courseDto) {
        Course course = courseMapper.toCourse(courseDto);
        course.setDepartment(departmentRepository.findById(courseDto.department_id()).orElseThrow());
        return courseRepository.save(course);
    }

    public List<Course> getCoursesByDepartment(String department_id) {
        Department department = departmentRepository.findById(department_id).orElseThrow();
        return department.getCourses();
    }

    public Course getCourse(String course_code) {
        return courseRepository.findById(course_code).orElseThrow();
    }

    public void deleteCourse(String course_code) {
        courseRepository.deleteById(course_code);
    }

    public List<Faculty> getFacultyOfCourse(String course_code) {
        return courseRepository.findById(course_code).orElseThrow().getFaculties();
    }
}
