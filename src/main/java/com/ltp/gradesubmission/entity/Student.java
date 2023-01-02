package com.ltp.gradesubmission.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name ="student")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "name",nullable = false)
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NonNull
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grade> gradeList;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns =@JoinColumn(name = "student_id",referencedColumnName = "id")
            ,inverseJoinColumns =@JoinColumn(name = "course_id",referencedColumnName = "id") )
    private Set<Course> courses;



}
