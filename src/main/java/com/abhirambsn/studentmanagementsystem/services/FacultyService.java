package com.abhirambsn.studentmanagementsystem.services;

import com.abhirambsn.studentmanagementsystem.dto.FacultyDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyResponseDto;
import com.abhirambsn.studentmanagementsystem.mappers.FacultyMapper;
import com.abhirambsn.studentmanagementsystem.models.Faculty;
import com.abhirambsn.studentmanagementsystem.repositories.CourseRepository;
import com.abhirambsn.studentmanagementsystem.repositories.DepartmentRepository;
import com.abhirambsn.studentmanagementsystem.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final FacultyMapper facultyMapper;


    public FacultyService(FacultyRepository facultyRepository, CourseRepository courseRepository, DepartmentRepository departmentRepository, FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.facultyMapper = facultyMapper;
    }

    public FacultyResponseDto registerFaculty(FacultyDto facultyDto) {
        Faculty faculty = facultyMapper.toFaculty(facultyDto);
        faculty.setDepartment(departmentRepository.findById(facultyDto.department_id()).orElseThrow());
        facultyRepository.save(faculty);

        return facultyMapper.fromFaculty(faculty);
    }

    public FacultyResponseDto getFaculty(String employee_id) {
        Faculty faculty = facultyRepository.findById(employee_id).orElseThrow();
        return facultyMapper.fromFaculty(faculty);
    }

    public boolean assignCourseToFaculty(String course_code, String faculty_id) {
        try {
            var course = courseRepository.findById(course_code).orElseThrow();
            var faculty = facultyRepository.findById(faculty_id).orElseThrow();

            faculty.getCourses().add(course);
            facultyRepository.save(faculty);

            return true;
        } catch (Exception e) {
            System.err.println("Error Occurred while Assignment of Course:" + e);
            return false;
        }
    }
}
