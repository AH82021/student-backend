package com.ltp.gradesubmission.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   @Column(name = "subject",nullable = false)
   @NonNull
   @NotBlank(message = "subject cannot be blank")
    private String subject;
   @NonNull
   @Column(name = "code",nullable = false)
   @NotBlank(message = "code cannot be blank")
    private String code;
   @NonNull
   @NotBlank (message = "description cannot be blank")
   @Column(name ="description",nullable = false,unique = true)
    private String description;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Grade> gradeList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns =@JoinColumn(name = "course_id",referencedColumnName = "id")
            ,inverseJoinColumns =@JoinColumn(name = "student_id",referencedColumnName = "id") )
    private Set<Student> students;





}
