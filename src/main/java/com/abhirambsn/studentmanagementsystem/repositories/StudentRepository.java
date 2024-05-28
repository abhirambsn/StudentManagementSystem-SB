package com.abhirambsn.studentmanagementsystem.repositories;

import com.abhirambsn.studentmanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "SELECT * FROM student LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Student> findByLimitAndOffset(int limit, int offset);

    @Query(value = "SELECT * FROM student WHERE branch_id = ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Student> findStudentsByBranchId(String branch_id, int limit, int offset);

    @Query(value = "SELECT * FROM student WHERE program_code = ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Student> findStudentsByProgramCode(String program_code, int limit, int offset);
}
