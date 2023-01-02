package com.ltp.gradesubmission.web;


import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.CourseService;
import com.ltp.gradesubmission.service.CourseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
@Valid
public class CourseController {

    CourseService courseService;
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody @Valid Course course) {

        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
         courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {

        return new ResponseEntity<>(courseService.getCourses(),HttpStatus.OK);
    }
   @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId,courseId),HttpStatus.OK);
   }
@GetMapping("/{courseId}/students")
   public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long courseId){
        return new ResponseEntity<>(courseService.getEnrolledStudents(courseId),HttpStatus.OK);
   }


}