package com.abhirambsn.studentmanagementsystem.services;

import com.abhirambsn.studentmanagementsystem.dto.StudentDto;
import com.abhirambsn.studentmanagementsystem.dto.StudentResponseDto;
import com.abhirambsn.studentmanagementsystem.mappers.StudentMapper;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.models.Student;
import com.abhirambsn.studentmanagementsystem.repositories.BranchRepository;
import com.abhirambsn.studentmanagementsystem.repositories.CourseRepository;
import com.abhirambsn.studentmanagementsystem.repositories.ProgramRepository;
import com.abhirambsn.studentmanagementsystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final BranchRepository branchRepository;
    private final ProgramRepository programRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, BranchRepository branchRepository, ProgramRepository programRepository, CourseRepository courseRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.branchRepository = branchRepository;
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto registerStudent(StudentDto studentDto) {
        Student newStudent = studentMapper.toStudent(studentDto);
        newStudent.setProgram(
                programRepository.findById(studentDto.program_code()).orElseThrow()
        );
        newStudent.setBranch(
                branchRepository.findById(studentDto.branch_id()).orElseThrow()
        );
        studentRepository.save(newStudent);
        return studentMapper.fromStudent(newStudent);
    }

    public StudentResponseDto getStudent(String id) {
        return studentMapper.fromStudent(
                studentRepository.findById(id).orElseThrow()
        );
    }

    public List<StudentResponseDto> getAllStudents(int offset, int limit) {
        return studentRepository.findByLimitAndOffset(limit, offset).stream()
                .map(studentMapper::fromStudent)
                .toList();
    }

    public List<StudentResponseDto> getStudentsByBranchId(String branch_id, int offset, int limit) {
        return studentRepository.findStudentsByBranchId(branch_id, limit, offset).stream()
                .map(studentMapper::fromStudent)
                .toList();
    }

    public boolean registerCourse(String enrolment_no, String course_code) {
        Student student = studentRepository.findById(enrolment_no).orElseThrow();
        Course c = courseRepository.findById(course_code).orElseThrow();
        student.getRegistered_courses().add(c);
        studentRepository.save(student);
        return true;
    }

    public List<StudentResponseDto> getStudentsByProgramCode(String program_code, int offset, int limit) {
        return studentRepository.findStudentsByProgramCode(program_code, limit, offset).stream()
                .map(studentMapper::fromStudent)
                .toList();
    }

    public boolean deactivateStudent(String enrolment_no) {
        try {
            Student student = studentRepository.findById(enrolment_no).orElseThrow();
            student.set_active(false);
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            System.err.println("Error Occurred while Deactivating Student:" + e);
            return false;
        }
    }

    public boolean reactivateStudent(String enrolment_no) {
        try {
            Student student = studentRepository.findById(enrolment_no).orElseThrow();
            student.set_active(true);
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            System.err.println("Error Occurred while Deactivating Student:" + e);
            return false;
        }
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
