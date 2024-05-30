package com.abhirambsn.studentmanagementsystem.services;

import com.abhirambsn.studentmanagementsystem.dto.AdminDto;
import com.abhirambsn.studentmanagementsystem.dto.AdminResponseDto;
import com.abhirambsn.studentmanagementsystem.mappers.AdminMapper;
import com.abhirambsn.studentmanagementsystem.models.Admin;
import com.abhirambsn.studentmanagementsystem.repositories.AdminRepository;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public AdminResponseDto createAdmin(AdminDto adminDto) {
        Admin newAdmin = adminMapper.toAdmin(adminDto);
        adminRepository.save(newAdmin);
        return adminMapper.fromAdmin(newAdmin);
    }

    public AdminResponseDto getAdminProfile(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        return adminMapper.fromAdmin(admin);
    }
}
