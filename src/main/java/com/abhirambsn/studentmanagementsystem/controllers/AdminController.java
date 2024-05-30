package com.abhirambsn.studentmanagementsystem.controllers;

import com.abhirambsn.studentmanagementsystem.dto.AdminDto;
import com.abhirambsn.studentmanagementsystem.dto.AdminResponseDto;
import com.abhirambsn.studentmanagementsystem.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<AdminResponseDto> createAdmin(
            @RequestBody AdminDto adminDto
    ) {
        return ResponseEntity.ok(adminService.createAdmin(adminDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDto> getAdminProfile(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(adminService.getAdminProfile(id));
    }
}
