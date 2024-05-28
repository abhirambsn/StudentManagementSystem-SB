package com.abhirambsn.studentmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "program_code")
    @JsonManagedReference
    private Program program;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonManagedReference
    private Branch branch;

    @Column(nullable = false)
    private int year_of_joining;

    @Column
    private boolean has_hostel;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    @JsonManagedReference
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> registered_courses;
}
