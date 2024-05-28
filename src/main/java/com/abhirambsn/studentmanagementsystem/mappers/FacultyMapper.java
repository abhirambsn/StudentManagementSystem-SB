package com.abhirambsn.studentmanagementsystem.mappers;

import com.abhirambsn.studentmanagementsystem.dto.FacultyDto;
import com.abhirambsn.studentmanagementsystem.dto.FacultyResponseDto;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.models.Faculty;
import com.abhirambsn.studentmanagementsystem.util.IdGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyMapper {
    private final PasswordEncoder passwordEncoder;

    public FacultyMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Faculty toFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        String employee_id = IdGenerator.generateFacultyId(facultyDto.department_id());

        faculty.setId(employee_id);
        faculty.setFirst_name(facultyDto.first_name());
        faculty.setLast_name(facultyDto.last_name());
        faculty.setGender(facultyDto.gender());
        faculty.setEmail(facultyDto.email());
        faculty.setPhone_number(facultyDto.phone_number());
        faculty.setDate_of_birth(facultyDto.date_of_birth());
        faculty.setDate_of_joining(facultyDto.date_of_joining());

        // Credentials
        faculty.setUsername(employee_id);
        String initPassword = IdGenerator.generateRandomPassword(facultyDto.first_name(), facultyDto.last_name());
        String initPwHash = passwordEncoder.encode(initPassword);
        faculty.setPassword(initPwHash);

        System.out.println("Credentials of " + faculty.getId() + " are: " + faculty.getUsername() + " " + initPassword);

        return faculty;
    }

    public FacultyResponseDto fromFaculty(Faculty faculty) {
        List<Course> courseList = faculty.getCourses();
        if (courseList == null) {
            return new FacultyResponseDto(
                    faculty.getId(),
                    faculty.getFirst_name(),
                    faculty.getLast_name(),
                    faculty.getGender(),
                    faculty.getEmail(),
                    faculty.getPhone_number(),
                    faculty.getDate_of_birth(),
                    faculty.getDate_of_joining(),
                    faculty.getDepartment().getDepartment_name(),
                    List.of()
            );
        } else {
            return new FacultyResponseDto(
                    faculty.getId(),
                    faculty.getFirst_name(),
                    faculty.getLast_name(),
                    faculty.getGender(),
                    faculty.getEmail(),
                    faculty.getPhone_number(),
                    faculty.getDate_of_birth(),
                    faculty.getDate_of_joining(),
                    faculty.getDepartment().getDepartment_name(),
                    faculty.getCourses().stream().map(Course::getCourse_name).toList()
            );
        }
    }
}
