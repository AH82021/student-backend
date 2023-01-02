package com.ltp.gradesubmission.entity;

import com.ltp.gradesubmission.validation.Score;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "grade",uniqueConstraints = {
        @UniqueConstraint(columnNames ={"student_id","course_id"} )
})
public class Grade {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 @Column(name ="score",nullable = false)
 @Score
    private String score;
 @ManyToOne(optional = false)
 @JoinColumn(name = "student_id",referencedColumnName = "id")
private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;


}
