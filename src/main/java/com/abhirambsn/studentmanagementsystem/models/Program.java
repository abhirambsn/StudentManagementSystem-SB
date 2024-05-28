package com.abhirambsn.studentmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="programs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    private String program_code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Student> students;


}
