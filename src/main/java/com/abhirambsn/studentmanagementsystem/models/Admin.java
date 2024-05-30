package com.abhirambsn.studentmanagementsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin extends User {
    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Date date_of_birth;

    @Column(nullable = false)
    private String designation;
}
