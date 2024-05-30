package com.abhirambsn.studentmanagementsystem.mappers;

import com.abhirambsn.studentmanagementsystem.dto.AdminDto;
import com.abhirambsn.studentmanagementsystem.dto.AdminResponseDto;
import com.abhirambsn.studentmanagementsystem.models.Admin;
import com.abhirambsn.studentmanagementsystem.models.Role;
import com.abhirambsn.studentmanagementsystem.util.IdGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminMapper {
    private final PasswordEncoder passwordEncoder;

    public AdminMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Admin toAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        String employee_id = IdGenerator.generateAdminId();
        String initPassword = IdGenerator.generateRandomPassword(adminDto.first_name(), adminDto.last_name());
        String initPwHash = passwordEncoder.encode(initPassword);

        admin.setId(employee_id);
        admin.setFirst_name(adminDto.first_name());
        admin.setLast_name(adminDto.last_name());
        admin.setEmail(adminDto.email());
        admin.setPhone_number(adminDto.phone_number());
        admin.setGender(adminDto.gender());
        admin.setDate_of_birth(adminDto.date_of_birth());
        admin.setDesignation(adminDto.designation());
        admin.setRole(Role.ADMIN);

        // Credentials
        admin.setUsername(employee_id);
        admin.setPassword(initPwHash);

        System.out.println("Credentials of " + admin.getId() + " are: " + employee_id + " " + initPassword);

        return admin;
    }

    public AdminResponseDto fromAdmin(Admin admin) {
        return new AdminResponseDto(
                admin.getId(),
                admin.getFirst_name(),
                admin.getLast_name(),
                admin.getEmail(),
                admin.getPhone_number(),
                admin.getGender(),
                admin.getDesignation(),
                admin.getDate_of_birth()
        );
    }
}
