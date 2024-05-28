package com.abhirambsn.studentmanagementsystem.mappers;

import com.abhirambsn.studentmanagementsystem.dto.StudentDto;
import com.abhirambsn.studentmanagementsystem.dto.StudentResponseDto;
import com.abhirambsn.studentmanagementsystem.models.Address;
import com.abhirambsn.studentmanagementsystem.models.Student;
import com.abhirambsn.studentmanagementsystem.util.IdGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public StudentMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Student toStudent(StudentDto studentDto) {
        Student student = new Student();
        String enrolmentNumber = IdGenerator.generateStudentEnrolmentNumber(
                studentDto.branch_id(),
                studentDto.program_code(),
                studentDto.year_of_joining()
        );

        student.setId(enrolmentNumber);
        student.setFirst_name(studentDto.first_name());
        student.setLast_name(studentDto.last_name());
        student.setGender(studentDto.gender());
        student.setEmail(studentDto.email());
        student.setPhone_number(studentDto.phone_number());

        Address addr = new Address();
        addr.setLine1(studentDto.line1());
        if (studentDto.line2() != null)
            addr.setLine2(studentDto.line2());
        else
            addr.setLine2(null);
        addr.setCity(studentDto.city());
        addr.setState(studentDto.state());
        addr.setDistrict(studentDto.district());
        addr.setCountry(studentDto.country());
        addr.setPostal_code(studentDto.postal_code());
        addr.setId(student.getId());

        student.setAddress(addr);

        // Credentials
        String initPassword = IdGenerator.generateRandomPassword(studentDto.first_name(), studentDto.last_name());
        String initPwHash = passwordEncoder.encode(initPassword);
        student.setUsername(enrolmentNumber);
        student.setPassword(initPwHash);

        System.out.println("Credentials of " + student.getId() + " are: " + student.getUsername() + " " + initPassword);

        student.setYear_of_joining(studentDto.year_of_joining());
        student.setHas_hostel(studentDto.has_hostel());

        return student;
    }

    public StudentResponseDto fromStudent(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getFirst_name(),
                student.getLast_name(),
                student.getEmail(),
                student.getPhone_number(),
                student.getProgram().getName(),
                student.getBranch().getBranch_name(),
                student.getYear_of_joining(),
                student.getAddress().getLine1(),
                student.isHas_hostel()
        );
    }
}
