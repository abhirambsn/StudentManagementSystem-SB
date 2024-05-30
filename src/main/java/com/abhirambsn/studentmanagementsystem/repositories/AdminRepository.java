package com.abhirambsn.studentmanagementsystem.repositories;

import com.abhirambsn.studentmanagementsystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
