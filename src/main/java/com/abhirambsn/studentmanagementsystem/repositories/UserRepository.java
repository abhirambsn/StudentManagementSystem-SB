package com.abhirambsn.studentmanagementsystem.repositories;

import com.abhirambsn.studentmanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
