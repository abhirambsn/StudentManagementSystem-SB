package com.abhirambsn.studentmanagementsystem.repositories;

import com.abhirambsn.studentmanagementsystem.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {
}
