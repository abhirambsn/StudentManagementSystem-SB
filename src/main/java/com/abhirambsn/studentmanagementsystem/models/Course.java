package com.abhirambsn.studentmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @Column(updatable = false)
    private String course_code;

    @Column(nullable = false)
    private String course_name;

    @Column(nullable = false)
    private String course_description_link;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private CourseType course_type;

    @Column(nullable = false)
    private CourseAssignmentType course_assignment_type;

    @Column
    private boolean isActive;

    @Column(nullable = false)
    private int year_of_start;

    @Column
    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Faculty> faculties;

    @ManyToMany(mappedBy = "registered_courses", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Student> students;


}
